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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
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
        AsyncImage(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 4.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
            model = ImageRequest.Builder(context)
                .data(viewModel.capturedImageUri)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.radiography),
        )

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
                    .padding(horizontal = 4.dp),
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
            .padding(20.dp)
    ) {

        Row(modifier = Modifier.padding(vertical = 8.dp),){

            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.warning),
                tint = extraIconColor2
            )


            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = stringResource(R.string.diagnosis),
                style = MaterialTheme.typography.titleMedium
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

        Row(modifier = Modifier.padding(vertical = 8.dp),){

            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = stringResource(R.string.warning),
                tint = extraIconColor3
            )


            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = stringResource(R.string.precision),
                style = MaterialTheme.typography.titleMedium
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
        shape = MaterialTheme.shapes.large
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)) {

            Icon(
                imageVector = Icons.Rounded.Warning,
                contentDescription = stringResource(R.string.warning),
                tint = extraIconColor1
            )

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(R.string.result_needs_radiologist)
            )

        }
    }
}