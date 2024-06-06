package com.jitendra.speakhomeassignment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jitendra.speakhomeassignment.R
import com.jitendra.speakhomeassignment.data.Course
import com.jitendra.speakhomeassignment.data.Day
import com.jitendra.speakhomeassignment.data.Info
import com.jitendra.speakhomeassignment.viewmodel.SpeakUIViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun SpeakUIContainer() {
    val viewModel: SpeakUIViewModel = hiltViewModel()
    val courseState = viewModel.uiState.collectAsState()
    when (val uiState = courseState.value) {
        is SpeakUIViewModel.UIState.CourseData -> {
            // Use uiState.courseData here
            DisplayCourseData(uiState.courseData, viewModel::onDayClick)
        }
        is SpeakUIViewModel.UIState.LoadSound -> {
            // Use uiState.sound here
        }
        is SpeakUIViewModel.UIState.Error -> {
            // Use uiState.message here
            ShowErrorState(uiState.message, {})
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun DisplayCourseData(
    courseData: Course?,
    onDayClick: (String) -> Unit,
) {
    if (courseData == null || courseData.units.isEmpty()) {
        ShowErrorState("No course data found. Try Again.", {})
        return
    }
//    val scrollState = rememberScrollState()
    Box(
//        Modifier.verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column {
            DisplayHeader(courseData.info)
            DisplayUnit(courseData.units[0], onDayClick)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun DisplayUnit(
    unit: com.jitendra.speakhomeassignment.data.Unit,
    onDayClick: (String) -> Unit,
) {
    Column(
        Modifier.fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        ImageFromDrawable(id = R.drawable.course_unit_default_icon, Modifier.padding(12.dp))
        Text("Unit ${unit.id.last() + 1}", Modifier.padding(8.dp))
        unit.days.forEach {
            DisplayDay(it, onDayClick)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun DisplayDay(
    day: Day,
    onDayClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "Day ${day.id.last() + 1}",
            Modifier.clickable { onDayClick(day.id) },
        )
        Card(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                RoundImageWithBackground(
                    day.thumbnailImageUrl,
                    Modifier
                        .wrapContentSize()
                        .size(100.dp)
                        .padding(8.dp)
                        .border(2.dp, Color.Gray, shape = CircleShape),
                )
                Column(
                    Modifier.wrapContentSize()
                        .padding(8.dp),
//                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(day.title)
                    Text(day.subtitle)
                }
                // ToDo: Display lessons
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun DisplayHeader(info: Info) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically),
        ) {
            ImageFromDrawable(
                id = R.drawable.course_default_header_background_vector,
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center),
            )
            RoundImageWithBackground(url = info.thumbnailImageUrl, Modifier.align(Alignment.Center))
        }
        Text(text = info.title)
        Text(text = info.subtitle)
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
fun ShowErrorState(
    message: String = "Something went wrong",
    onClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message)
            Button(onClick = onClick) {
                Text(text = "Retry")
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RoundImageWithBackground(
    url: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray,
) {
    Box(
        modifier =
            modifier
                .background(backgroundColor, shape = CircleShape)
                .clip(CircleShape),
    ) {
        val painter = rememberImagePainter(data = url)
        Image(
            painter = painter,
            contentDescription = null, // consider providing a meaningful description
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ImageFromDrawable(
    id: Int,
    modifier: Modifier = Modifier,
) {
    val image = painterResource(id = id)
    Image(
        painter = image,
        contentDescription = "Description for accessibility",
        modifier = modifier,
    )
}
