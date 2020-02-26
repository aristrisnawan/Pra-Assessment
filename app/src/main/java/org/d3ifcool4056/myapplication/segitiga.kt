package org.d3ifcool4056.myapplication


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3ifcool4056.myapplication.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class segitiga : Fragment() {
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSegitigaBinding>(inflater,R.layout.fragment_segitiga, container, false)
        if (savedInstanceState != null ){
            keliling = savedInstanceState.getDouble("luas")
            luas = savedInstanceState.getDouble("keliling")

            binding.keliling.text=keliling.toString()
            binding.luas.text=luas.toString()
        }
        binding.btnHitung.setOnClickListener {
            var alas = binding.editAlas.text.toString().toDouble()
            var tinggi = binding.editTinggi.text.toString().toDouble()

            luas = (alas*tinggi)/2
            var sisiMiring = Math.sqrt(Math.pow(alas,2.0)+(Math.pow(tinggi,2.0)))
            keliling = alas+tinggi+sisiMiring

            binding.keliling.text=keliling.toString()
            binding.luas.text=luas.toString()

        }
        binding.share.setOnClickListener {
            onShare()
        }

        return  binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas",luas)
        outState.putDouble("keliling",keliling)
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
            Toast.makeText(this.activity,"not found",LENGTH_LONG ).show()
        }
    }


}
