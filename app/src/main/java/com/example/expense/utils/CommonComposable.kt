package com.example.expense.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense.R

object CommonComposable {

    @Composable
    fun EditTextField(
        label: String,
        text: String,
        onTextChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        TextField(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .heightIn(48.dp),
            value = text,
            onValueChange = onTextChange,
            label = { Text(text = label, color = colorResource(id = R.color.grey_969696)) },
            shape = RoundedCornerShape(11.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.white_F4F4F4),
                unfocusedContainerColor = colorResource(id = R.color.white_F4F4F4),
                focusedIndicatorColor = Color.Transparent, // No underline when focused
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
    @Composable
    fun SubmitButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(
            onClick = onClick,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .heightIn(48.dp),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.purple_9F46FF),
                disabledContentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.purple_9F46FF)
            ),
            shape = RoundedCornerShape(11.dp)
        ) {
            Text(text = label)
        }
    }

    @Composable
    fun ApiProgressBar(modifier: Modifier = Modifier){
        CircularProgressIndicator(
            modifier = modifier.width(32.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
    @Composable
    fun BackButtonAndText(modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically // Align text and icon vertically
        ) {
            // Icon on the left
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Favorite icon",
                modifier = Modifier.size(24.dp) // Set the size of the icon
            )

            // Space between icon and text
            Spacer(modifier = Modifier.width(8.dp))

            // Text on the right
            Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}