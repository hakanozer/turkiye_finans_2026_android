package com.works.project.ui.todo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.works.project.R
import com.works.project.data.remote.TodoApi
import com.works.project.databinding.ActivityTodoBinding
import com.works.project.domain.model.TodoData
import com.works.project.domain.model.TodoEntity
import com.works.project.domain.utils.ApiClient
import com.works.project.domain.utils.AppDatabase
import com.works.project.domain.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class TodoActivity : BaseActivity() {

    @Inject
    lateinit var todoApi: TodoApi

    private lateinit var db: AppDatabase

    private lateinit var binding: ActivityTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tdBtnAdd.setOnClickListener {
            val title = binding.tdTxtTitle.text.toString().trim()
            val todoEntity = TodoEntity()
            todoEntity.title = title
            todoEntity.id = 0
            todoEntity.completed = true
            todoEntity.uid = 0
            fncAddTodo(todoEntity)
        }

        todoApi.getTodos().enqueue(object : Callback<TodoData> {
            override fun onResponse(
                call: Call<TodoData?>,
                response: Response<TodoData?>
            ) {
                val arr = response.body()?.data
                for (item in arr!!) {
                    Log.d("todoApi", "onResponse: " + item.title)
                    val todoEntity = TodoEntity()
                    todoEntity.title = item.title
                    todoEntity.id = item.id.toInt()
                    todoEntity.completed = item.completed
                    todoEntity.uid = item.uid.toInt()
                    fncAddTodo(todoEntity)
                }
            }

            override fun onFailure(
                call: Call<TodoData?>,
                t: Throwable
            ) {
                Log.e("todoApi error", "onResponse: " + t.message)
            }

        })

    }

    fun fncAddTodo(todoEntity: TodoEntity) {
        lifecycleScope.launch{
            withContext(Dispatchers.IO) {
                val status = db.todoDao().insert(todoEntity)
                Log.d("status", "fncAddTodo: " + status)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume - 1", "onResume: 1")
    }
}