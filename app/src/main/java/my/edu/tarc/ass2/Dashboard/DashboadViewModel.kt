package my.edu.tarc.ass2.Dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.tarc.mycontact.Bill
import my.tarc.mycontact.Contact

class DashboardViewModel : ViewModel() {
    //LiveData gives us updated contacts when they change
    var contactList : LiveData<List<Bill>>
    private val repository: ContactRepository
    var selectedIndex: Int = -1

    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        contactList = repository.allContacts
    }

    fun addContact(contact: Contact) = viewModelScope.launch{
        repository.add(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch{
        repository.delete(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch{
        repository.update(contact)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }


}