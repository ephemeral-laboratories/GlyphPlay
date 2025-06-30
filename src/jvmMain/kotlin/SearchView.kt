package garden.ephemeral.glyphplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import garden.ephemeral.glyphplay.search.UnicodeIndices
import garden.ephemeral.glyphplay.unicode.CodePoint
import garden.ephemeral.glyphs.glyphplay.generated.resources.Res
import garden.ephemeral.glyphs.glyphplay.generated.resources.icon_clear
import garden.ephemeral.glyphs.glyphplay.generated.resources.icon_search
import garden.ephemeral.glyphs.glyphplay.generated.resources.label_invalid_search_query
import garden.ephemeral.glyphs.glyphplay.generated.resources.label_search_code_points
import garden.ephemeral.glyphs.glyphplay.generated.resources.placeholder_no_code_points_found
import org.apache.lucene.queryparser.classic.ParseException
import org.jetbrains.compose.resources.stringResource

@Composable
private fun SearchTextField(
    isError: Boolean,
    setIsError: (Boolean) -> Unit,
    onExecuteSearch: (query: String) -> Unit,
) {
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
private fun SearchResultsGrid(codePoints: List<CodePoint>, onCodePointClicked: (CodePoint) -> Unit) {
    val scrollState = rememberLazyGridState()

    LaunchedEffect(codePoints) {
        scrollState.scrollToItem(index = 0)
    }

    LazyVerticalGrid(
        columns = GridCells.FixedSize(48.dp),
        state = scrollState,
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
private fun NoSearchResultsPlaceholder() {
    Text(
        text = stringResource(Res.string.placeholder_no_code_points_found),
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Composable
fun SearchView(onCodePointClicked: (CodePoint) -> Unit) {
    val (searchResults, setSearchResults) = remember { mutableStateOf<List<CodePoint>>(emptyList()) }
    val (isError, setIsError) = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SearchTextField(isError = isError, setIsError = setIsError) { query ->
            if (query.isBlank()) {
                setSearchResults(emptyList())
            } else {
                try {
                    val results = UnicodeIndices.instance.search(query)
                    setSearchResults(results.toList())
                } catch (_: ParseException) {
                    setIsError(true)
                }
            }
        }
        if (searchResults.isNotEmpty()) {
            SearchResultsGrid(
                codePoints = searchResults,
                onCodePointClicked = onCodePointClicked,
            )
        } else {
            NoSearchResultsPlaceholder()
        }
    }
}
