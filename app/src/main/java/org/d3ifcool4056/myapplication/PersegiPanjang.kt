package org.d3ifcool4056.myapplication


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3ifcool4056.myapplication.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjang : Fragment() {
    private var lluas = 0
    private var kkeliling = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater,R.layout.fragment_persegi_panjang, container, false)
            if (savedInstanceState != null ){
                lluas=savedInstanceState.getInt("luas")
                kkeliling=savedInstanceState.getInt("keliling")

                binding.luas.text=lluas.toString()
                binding.keliling.text=kkeliling.toString()
            }
           binding.button.setOnClickListener {
               var panjang = binding.editPanjang.text.toString().toInt()
               var lebar = binding.editLebar.text.toString().toInt()

               lluas = panjang*lebar
               kkeliling = 2 *(panjang+lebar)

               binding.keliling.text=kkeliling.toString()
               binding.luas.text=lluas.toString()
           }

        binding.share.setOnClickListener {
            onShare()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("luas",lluas)
        outState.putInt("keliling",kkeliling)
        super.onSaveInstanceState(outState)
    }
    private fun onShare() {
        val shareIntent = this.activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText("hallo")
                .setType("text/plain")
                .intent
        }
        try {
            startActivity(shareIntent)
        } catch (ex : ActivityNotFoundException) {
            Toast.makeText(this.activity,"not found", Toast.LENGTH_LONG).show()
        }
    }

}
