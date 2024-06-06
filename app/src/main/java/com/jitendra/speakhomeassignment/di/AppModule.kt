package com.jitendra.speakhomeassignment.di

import android.content.Context
import com.jitendra.speakhomeassignment.data.CourseData
import com.jitendra.speakhomeassignment.data.MockAsrData
import com.jitendra.speakhomeassignment.data.MockCourseData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(
        @ApplicationContext appContext: Context,
    ): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideCourseData(
        @ApplicationContext appContext: Context,
    ): CourseData {
        return MockCourseData(appContext)
    }

    @Singleton
    @Provides
    fun provideAsrData(
        @ApplicationContext appContext: Context,
    ): MockAsrData {
        return MockAsrData(appContext)
    }
}
