package com.qnecesitas.pedianeumx.ui.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.qnecesitas.pedianeumx.R
import com.qnecesitas.pedianeumx.datamodel.Diagnosis.*
import com.qnecesitas.pedianeumx.ui.theme.extraIconColor1
import com.qnecesitas.pedianeumx.ui.theme.extraIconColor2
import com.qnecesitas.pedianeumx.ui.theme.extraIconColor3

@Composable
fun ResultView(
    viewModel: IResultViewModel
){
    Column(modifier = Modifier.fillMaxSize()) {

        val context = LocalContext.current
        Surface (
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 4.dp)
                .sizeIn(maxHeight = 300.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.extraLarge
                ),
            shape = MaterialTheme.shapes.extraLarge
        ){
            AsyncImage(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge),
                model = ImageRequest.Builder(context)
                    .data(viewModel.capturedImageUri)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.radiography),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            ResultDetails(viewModel)

            WarningConfirmWithDoctor(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            )

        }
    }

}

@Composable
private fun ResultDetails(
    viewModel: IResultViewModel
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 52.dp)
    ) {

        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = stringResource(R.string.results),
            style = MaterialTheme.typography.titleLarge
        )

        Row(modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.diagnosis_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                contentDescription = stringResource(R.string.diagnosis),
                colorFilter = ColorFilter.tint(extraIconColor2)
            )


            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = stringResource(R.string.diagnosis),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = when(viewModel.diagnosisResult){
                    PNEUMONIA -> stringResource(R.string.pneumonia)
                    NON_PNEUMONIA -> stringResource(R.string.non_pneumonia)
                    null -> String()
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically){

            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.straighten_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                contentDescription = stringResource(R.string.diagnosis),
                colorFilter = ColorFilter.tint(extraIconColor3)
            )


            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = stringResource(R.string.precision),
                style = MaterialTheme.typography.bodyMedium
            )

            if(viewModel.diagnosisPrecision != 0.0) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = "${viewModel.diagnosisPrecision}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }

}

@Composable
fun WarningConfirmWithDoctor(
    modifier: Modifier = Modifier
){
    Surface (
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.inversePrimary,
        shadowElevation = 4.dp
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.Rounded.Warning,
                contentDescription = stringResource(R.string.warning),
                tint = extraIconColor1
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.result_needs_radiologist),
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}