package de.syntax_institut.telefonbuch.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.syntax_institut.telefonbuch.R
import de.syntax_institut.telefonbuch.data.model.Contact
import de.syntax_institut.telefonbuch.databinding.FragmentDetailContactBinding

class DetailFragment : Fragment(){

    private lateinit var binding: FragmentDetailContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val contact = requireArguments().getParcelable<Contact>("contact")

        //Package Detailansicht Kontakte
        if (contact != null) {
            binding.detailPhonenumText.setText(contact.number)
            binding.detailNameTest.setText(contact.name)
            binding.detailContactIv.setImageResource(contact.image)
        } else {
            binding.detailContactIv.setImageResource(R.drawable.ic_baseline_broken_image_24)
        }

        //Telefonbutton gibt Hinweis
        binding.detailCallButton.setOnClickListener(){

            // Hinweis für Anrufer
            Toast.makeText(requireContext(), "Ein Moment, dann geht es weiter...",
                Toast.LENGTH_SHORT).show()

            //Call-Intent -- Telefonapplikation
            val number = binding.detailPhonenumText.setText(contact!!.number)
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.fromParts("tel", number.toString(), null)
            val shareIntent = Intent.createChooser(intent, "Telefon")
            startActivity(shareIntent)
        }

        //Editieren, Verweis auf Editansicht
        binding.detailEditButton.setOnClickListener(){
            //findNavController().navigate(DetailFragmentDirection.actionDetailFragmentEditFragment (name, number)

        }
    }
}