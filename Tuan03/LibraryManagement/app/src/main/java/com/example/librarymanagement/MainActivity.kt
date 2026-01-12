package com.example.librarymanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ===== OOP =====
data class Book(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)

data class Staff(
    var name: String
)

enum class BottomTab(val title: String) {
    Manage("Quản lý"),
    Books("DS Sách"),
    Staff("Nhân viên")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LibraryApp()
            }
        }
    }
}

@Composable
fun LibraryApp() {

    var currentTab by remember { mutableStateOf(BottomTab.Manage) }

    // ===== Shared State =====
    var staff by remember { mutableStateOf(Staff("Nguyen Van A")) }

    val books = remember {
        mutableStateListOf(
            Book(1, "Sách 01"),
            Book(2, "Sách 02")
        )
    }

    var bookCounter by remember { mutableStateOf(3) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentTab == BottomTab.Manage,
                    onClick = { currentTab = BottomTab.Manage },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Quản lý") }
                )
                NavigationBarItem(
                    selected = currentTab == BottomTab.Books,
                    onClick = { currentTab = BottomTab.Books },
                    icon = { Icon(Icons.Default.List, null) },
                    label = { Text("DS Sách") }
                )
                NavigationBarItem(
                    selected = currentTab == BottomTab.Staff,
                    onClick = { currentTab = BottomTab.Staff },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Nhân viên") }
                )
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (currentTab) {
                BottomTab.Manage -> ManageScreen(
                    staff = staff,
                    books = books,
                    onStaffChange = { staff = it },
                    onToggleBook = { book ->
                        val index = books.indexOf(book)
                        books[index] = book.copy(isSelected = !book.isSelected)
                    },
                    onAddBook = {
                        books.add(
                            Book(
                                id = bookCounter,
                                name = "Sách ${bookCounter.toString().padStart(2, '0')}"
                            )
                        )
                        bookCounter++
                    }
                )

                BottomTab.Books -> BookListScreen(books)
                BottomTab.Staff -> StaffScreen(staff)
            }
        }
    }
}

// ===== TAB 1: QUẢN LÝ =====
@Composable
fun ManageScreen(
    staff: Staff,
    books: List<Book>,
    onStaffChange: (Staff) -> Unit,
    onToggleBook: (Book) -> Unit,
    onAddBook: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Nhân viên")

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = staff.name,
                onValueChange = { onStaffChange(staff.copy(name = it)) },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {}) {
                Text("Đổi")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Danh sách sách")

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            LazyColumn(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(books) { book ->
                    BookItem(book) {
                        onToggleBook(book)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onAddBook,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Thêm")
        }
    }
}

// ===== TAB 2: DS SÁCH =====
@Composable
fun BookListScreen(books: List<Book>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Danh sách sách", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(books) { book ->
                Text(
                    text = "${book.name} - ${if (book.isSelected) "Đã chọn" else "Chưa chọn"}"
                )
            }
        }
    }
}

// ===== TAB 3: NHÂN VIÊN =====
@Composable
fun StaffScreen(staff: Staff) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Thông tin nhân viên", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Tên: ${staff.name}")
    }
}

// ===== BOOK ITEM =====
@Composable
fun BookItem(
    book: Book,
    onCheckedChange: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = book.isSelected,
                onCheckedChange = { onCheckedChange() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(book.name)
        }
    }
}
