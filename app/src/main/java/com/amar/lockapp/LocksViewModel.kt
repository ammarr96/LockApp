package com.amar.lockapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.lockapp.model.Lock
import com.amar.lockapp.repository.LockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocksViewModel @Inject constructor(
    private val lockRepository: LockRepository
): ViewModel() {

    private val _lockList = MutableStateFlow(listOf<Lock>())

    private val _lockListFiltered = MutableStateFlow(listOf<Lock>())
    val lockListFiltered = _lockListFiltered.asStateFlow()

    private val _showProgress = MutableStateFlow(false)
    val showProgress = _showProgress.asStateFlow()

    fun getLocks() {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.value = true
            val newData = lockRepository.getLocks()
            _lockList.value = newData.toList()
            _lockListFiltered.value = newData.toList()
            _showProgress.value = false
        }
    }

    fun getLocksByFilter(query: String? = null) {
        _lockListFiltered.value = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.value = true

            var newData = _lockList.value

            query?.let {  query ->
                newData = newData.filter { it.name.contains(query, true) }
            }

            _lockListFiltered.value = newData.toList()
            _showProgress.value = false
        }
    }

}