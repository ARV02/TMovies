package com.example.tmovies.ui.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tmovies.R
import com.example.tmovies.databinding.FragmentSettingsBinding
import com.example.tmovies.databinding.LayoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var bottomSheet: LayoutBottomSheetBinding
    private lateinit var imageUri: Uri
    
    companion object {
        private const val REQUEST_PERMISSION = 100
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_PICK_IMAGE = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openBottomSheet()
        openMapsActivity()
    }

    private fun openBottomSheet() {
        binding.floatingActionButton.setOnClickListener {
            val builder = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
            bottomSheet = LayoutBottomSheetBinding.inflate(layoutInflater)
            builder.setContentView(bottomSheet.root)
            builder.show()
            checkPermissions()
            bottomSheet.gallery.setOnClickListener {
                openGallery()
                builder.hide()
            }
        }
    }

    private fun uploadImage(fileUri: Uri) {
        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage(getString(R.string.uploading_file))
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
        storageReference.putFile(fileUri)
            .addOnSuccessListener {
                binding.photo.setImageURI(fileUri)
                Toast.makeText(requireContext(), getString(R.string.image_saved_success), Toast.LENGTH_LONG)
                    .show()
                if(progressDialog.isShowing) progressDialog.dismiss()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), getString(R.string.can_not_save_image), Toast.LENGTH_LONG)
                    .show()
                if(progressDialog.isShowing) progressDialog.dismiss()
            }
    }
    
    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        }
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun openGallery() {
        Intent(Intent.ACTION_GET_CONTENT).also { intent ->
            intent.type = "image/*"
            intent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(intent, REQUEST_PICK_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_IMAGE_CAPTURE) {
                imageUri = data?.data!!
                binding.photo.setImageURI(imageUri)
            } else if(requestCode == REQUEST_PICK_IMAGE && data != null && data.data !=null) {
                val fileUri = data.data
                binding.photo.setImageURI(fileUri)
                fileUri?.let { uploadImage(it) }
            }
        }
    }

    private fun openMapsActivity() {
        binding.location.setOnClickListener {
            val intent = Intent(requireContext(), MapsActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}