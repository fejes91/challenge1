package com.example.challenge1.navigation

import androidx.lifecycle.ViewModel
import com.example.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigatorViewModel @Inject constructor(val navigator: Navigator) : ViewModel()