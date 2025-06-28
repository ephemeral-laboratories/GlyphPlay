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
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import garden.ephemeral.glyphs.glyphplay.generated.resources.icon_clear
import garden.ephemeral.glyphs.glyphplay.generated.resources.icon_search
import garden.ephemeral.glyphs.glyphplay.generated.resources.label_invalid_search_query
import garden.ephemeral.glyphs.glyphplay.generated.resources.label_search_code_points
import garden.ephemeral.glyphs.glyphplay.generated.resources.placeholder_no_code_points_found
import org.apache.lucene.queryparser.classic.ParseException
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        state = rememberWindowState(size = DpSize(1200.dp, 800.dp)),
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
                    val (currentCodePoint, setCurrentCodePoint) = remember { mutableStateOf(CodePoint(0x1F574)) }
                    val searchResults = remember { mutableStateListOf<CodePoint>() }
                    val (isError, setIsError) = remember { mutableStateOf(false) }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SearchTextField(isError = isError, setIsError = setIsError) { query ->
                            if (query.isBlank()) {
                                searchResults.clear()
                            } else {
                                try {
                                    val results = UnicodeIndices.instance.search(query)
                                    searchResults.clear()
                                    searchResults.addAll(results)
                                } catch (_: ParseException) {
                                    setIsError(true)
                                }
                            }
                        }
                        if (searchResults.isNotEmpty()) {
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
fun SearchTextField(isError: Boolean, setIsError: (Boolean) -> Unit, onExecuteSearch: (query: String) -> Unit) {
    val (textFieldValue, setTextFieldValue) = remember { mutableStateOf(TextFieldValue()) }
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            setTextFieldValue(newValue)
            setIsError(false)
        },
        label = @Composable { Text(stringResource(Res.string.label_search_code_points)) },
        supportingText = if (isError) {
            @Composable { Text(stringResource(Res.string.label_invalid_search_query)) }
        } else {
            null
        },
        isError = isError,
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = { onExecuteSearch(textFieldValue.text.trim()) }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        modifier = Modifier
            .requiredWidth(48.dp * 7 + 8.dp * 6)
            .focusRequester(focusRequester),
        trailingIcon = @Composable {
            Row {
                if (textFieldValue.text.trim().isNotEmpty()) {
                    IconButton(onClick = {
                        setTextFieldValue(TextFieldValue())
                        setIsError(false)
                        focusRequester.requestFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(Res.string.icon_clear),
                        )
                    }
                }
                IconButton(onClick = {
                    onExecuteSearch(textFieldValue.text.trim())
                    focusRequester.requestFocus()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(Res.string.icon_search),
                    )
                }
            }
        }
    )
}

@Composable
fun SearchResultsGrid(codePoints: List<CodePoint>, onCodePointClicked: (CodePoint) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.FixedSize(48.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .requiredWidth(48.dp * 7 + 8.dp * 6)
            .fillMaxHeight()
    ) {
        items(items = codePoints, key = { it }) { codePoint ->
            CodePointCell(
                codePoint = codePoint,
                size = 48.dp,
                onClick = { onCodePointClicked(codePoint) },
                modifier = Modifier.animateItem(),
            )
        }
    }
}

@Composable
fun NoSearchResultsPlaceholder() {
    Text(
        text = stringResource(Res.string.placeholder_no_code_points_found),
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

