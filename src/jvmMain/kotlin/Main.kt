package garden.ephemeral.glyphplay

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import garden.ephemeral.glyphplay.search.UnicodeIndices
import garden.ephemeral.glyphplay.theme.AppTheme
import garden.ephemeral.glyphplay.theme.Branding

fun main() = application {
    Window(
        state = rememberWindowState(size = DpSize(1200.dp, 600.dp)),
        title = Branding.APPLICATION_NAME,
        onCloseRequest = ::exitApplication
    ) {
        AppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    // App state
                    val (currentCodePoint, setCurrentCodePoint) = remember { mutableStateOf(0x1F574) }
                    val searchResults = remember { mutableStateListOf<Int>() }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SearchTextField { query ->
                            val results = UnicodeIndices.instance.search(query)
                            searchResults.clear()
                            searchResults.addAll(results)
                        }
                        if (searchResults.size > 0) {
                            SearchResultsGrid(
                                codePoints = searchResults,
                                onCodePointClicked = setCurrentCodePoint,
                            )
                        } else {
                            NoSearchResultsPlaceholder()
                        }
                    }
                    Box(Modifier.fillMaxWidth()) {
                        val scrollBarState = rememberScrollState()
                        Box(Modifier.verticalScroll(state = scrollBarState)) {
                            CodePointDescriptionView(
                                codePoint = currentCodePoint,
                                onCodePointLinkClicked = setCurrentCodePoint
                            )
                        }
                        VerticalScrollbar(
                            adapter = rememberScrollbarAdapter(scrollBarState),
                            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTextField(onExecuteSearch: (query: String) -> Unit) {
    val (textFieldValue, setTextFieldValue) = remember { mutableStateOf(TextFieldValue()) }
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = setTextFieldValue,
        label = { Text("Search code points") },
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = { onExecuteSearch(textFieldValue.text.trim()) }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        modifier = Modifier
            .requiredWidth(48.dp * 7 + 8.dp * 6)
            .focusRequester(focusRequester),
        trailingIcon = {
            Row {
                if (textFieldValue.text.trim().isNotEmpty()) {
                    IconButton(onClick = {
                        setTextFieldValue(TextFieldValue())
                        focusRequester.requestFocus()
                    }) {
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear icon")
                    }
                }
                IconButton(onClick = {
                    onExecuteSearch(textFieldValue.text.trim())
                    focusRequester.requestFocus()
                }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
                }
            }
        }
    )
}

@Composable
fun SearchResultsGrid(codePoints: List<Int>, onCodePointClicked: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.FixedSize(48.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .requiredWidth(48.dp * 7 + 8.dp * 6)
            .fillMaxHeight()
    ) {
        items(codePoints) { codePoint ->
            CodePointCell(codePoint, 48.dp, onClick = { onCodePointClicked(codePoint) })
        }
    }
}

@Composable
fun NoSearchResultsPlaceholder() {
    Text(
        text = "No code points found.",
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

