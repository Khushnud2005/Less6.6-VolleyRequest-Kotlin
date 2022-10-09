package uz.example.less66_volleyrequest_kotlin.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.example.less66_volleyrequest_kotlin.R
import uz.example.less66_volleyrequest_kotlin.model.Employee
import uz.example.less66_volleyrequest_kotlin.network.VolleyHandler
import uz.example.less66_volleyrequest_kotlin.network.VolleyHttp

class MainActivity : AppCompatActivity() {
    lateinit var tv_res:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    fun initViews(){
        val btn_list = findViewById<Button>(R.id.btn_list)
        val btn_get_one = findViewById<Button>(R.id.btn_get_one)
        val btn_create = findViewById<Button>(R.id.btn_create)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_delete = findViewById<Button>(R.id.btn_delete)
        val et_get_id = findViewById<EditText>(R.id.et_get_id)
        val et_create_name = findViewById<EditText>(R.id.et_create_name)
        val et_create_salary = findViewById<EditText>(R.id.et_create_salary)
        val et_create_age = findViewById<EditText>(R.id.et_create_age)
        val et_update_name = findViewById<EditText>(R.id.et_update_name)
        val et_update_salary = findViewById<EditText>(R.id.et_update_salary)
        val et_update_age = findViewById<EditText>(R.id.et_update_age)
        val et_update_id = findViewById<EditText>(R.id.et_update_id)
        val et_delete_id = findViewById<EditText>(R.id.et_delete_id)
        tv_res = findViewById<TextView>(R.id.tv_res)


        btn_list.setOnClickListener(View.OnClickListener { getEmployeeList() })

        btn_get_one.setOnClickListener(View.OnClickListener {
            val id: String = et_get_id.getText().toString().trim { it <= ' ' }
            if (!id.isEmpty()) {
                getOneEmployee(id)
            }
        })

        btn_create.setOnClickListener(View.OnClickListener {
            val name = et_create_name.text.toString().trim { it <= ' ' }
            val salary = et_create_salary.text.toString().trim { it <= ' ' }
            val age = et_create_age.text.toString().trim { it <= ' ' }

            if (!salary.isEmpty() && !age.isEmpty()) {
                val emp = Employee(name, salary.toInt(), age.toInt())
                employeeCreate(emp)
            }
        })
        btn_update.setOnClickListener(View.OnClickListener {
            val id = et_update_id.text.toString().trim { it <= ' ' }
            val name = et_update_name.text.toString().trim { it <= ' ' }
            val salary = et_update_salary.text.toString().trim { it <= ' ' }
            val age = et_update_age.text.toString().trim { it <= ' ' }

            if (!id.isEmpty() && !salary.isEmpty() && !age.isEmpty()) {
                val emp = Employee(id.toInt(), name, salary.toInt(), age.toInt())
                employeeUpdate(emp)
            }
        })

        btn_delete.setOnClickListener(View.OnClickListener {
            val id: String = et_delete_id.getText().toString().trim { it <= ' ' }
            if (!id.isEmpty()) {
                employeeDelete(id)
            }
        })
    }

    private fun getEmployeeList() {
        VolleyHttp.get(
            VolleyHttp.API_LIST_EMPLOYEE,
            VolleyHttp.paramsEmpty(),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    tv_res.text = response
                }
                override fun onError(error: String?) {tv_res.text = error}
            })
    }

    private fun getOneEmployee(id: String) {
        VolleyHttp.get(
            VolleyHttp.API_SINGLE_EMPLOYEE.toString() + id,
            VolleyHttp.paramsEmpty(),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    tv_res.text = response
                }
                override fun onError(error: String?) {tv_res.text = error}
            })
    }

    private fun employeeDelete(id: String) {
        VolleyHttp.del(VolleyHttp.API_DELETE_EMPLOYEE.toString() + id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                tv_res.text = response
            }
            override fun onError(error: String?) {tv_res.text = error}
        })
    }

    private fun employeeCreate(employee: Employee) {
        VolleyHttp.post(
            VolleyHttp.API_CREATE_EMPLOYEE,
            VolleyHttp.paramsCreate(employee),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    tv_res.text = response
                }
                override fun onError(error: String?) {tv_res.text = error}
            })
    }

    private fun employeeUpdate(employee: Employee) {
        VolleyHttp.put(
            VolleyHttp.API_UPDATE_EMPLOYEE + employee.id,
            VolleyHttp.paramsUpdate(employee),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    tv_res.text = response
                }
                override fun onError(error: String?) {tv_res.text = error}
            })
    }
}