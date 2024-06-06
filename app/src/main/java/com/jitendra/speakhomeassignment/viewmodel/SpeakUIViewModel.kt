package com.jitendra.speakhomeassignment.viewmodel

import androidx.lifecycle.ViewModel
import com.jitendra.speakhomeassignment.data.Course
import com.jitendra.speakhomeassignment.data.CourseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SpeakUIViewModel
    @Inject
    constructor(
        courseData: CourseData,
    ) : ViewModel() {
        fun onDayClick(day: String) {
            // Handle day click here
        }

        private val _uiState = MutableStateFlow<UIState>(UIState.CourseData(courseData.loadCourseData()))
        val uiState = _uiState

        init {
        }
        // Use courseData here

        sealed class UIState {
            data class CourseData(val courseData: Course?) : UIState()

            data class LoadSound(val sound: String) : UIState()

            data class Error(val message: String) : UIState()
        }
    }
