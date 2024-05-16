    package com.example.toolbar

    import android.graphics.Color
    import android.os.Bundle
    import android.view.Menu
    import android.view.MenuItem
    import androidx.appcompat.app.AppCompatActivity
    import com.example.toolbar.databinding.ActivityMainBinding
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView

    class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding
        private var layoutManager: RecyclerView.LayoutManager? = null
        private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //These had to be added
            setSupportActionBar(binding.toolbar)

            binding.collapsingToolbar.title = "Toolbar Title"
            binding.collapsingToolbar.setContentScrimColor(Color.GREEN)

            layoutManager = LinearLayoutManager(this)
            binding.contentMain.recyclerView.layoutManager = layoutManager
            val data = Data() // Your data class instance
            adapter = RecyclerAdapter(data)
            binding.contentMain.recyclerView.adapter = adapter
        }

        //This had to be added to the recycleview project because we added the toolbar.
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            return when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
        }
    }
