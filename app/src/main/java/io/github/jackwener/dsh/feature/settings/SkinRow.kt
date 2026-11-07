package io.github.jackwener.dsh.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.jackwener.dsh.R
import io.github.jackwener.dsh.core.designsystem.Dsh
import io.github.jackwener.dsh.core.designsystem.DshSkin
import io.github.jackwener.dsh.core.designsystem.DshType
import io.github.jackwener.dsh.core.designsystem.Hairline
import io.github.jackwener.dsh.core.designsystem.Radius
import io.github.jackwener.dsh.core.designsystem.circleBg

/**
 * 设置 → 通用 → 外观 里的主题皮肤选择器：默认 + dream-skin 的 8 套皮肤。
 *
 * 每格是一个小色块（皮肤页面底色）、一个强调色圆点和 "Aa"（皮肤主文字色），
 * 和网页版 dream-skin 的皮肤卡片同一套配色；点一下立即生效（DshTheme 重组）。
 */
@Composable
fun SkinRow(selected: String, onSelect: (String) -> Unit, modifier: Modifier = Modifier) {
    val c = Dsh.colors
    Row(
        modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SkinChip(
            label = stringResource(R.string.skin_default),
            bg = c.bgBase,
            accent = c.business,
            fg = c.labelPrimary,
            selected = selected.isEmpty(),
            onClick = { onSelect("") },
        )
        DshSkin.all.forEach { skin ->
            SkinChip(
                label = stringResource(skin.nameRes),
                bg = DshSkin.parseCssColor(skin.bgCss) ?: c.bgBase,
                accent = DshSkin.parseCssColor(skin.accentCss) ?: c.business,
                fg = DshSkin.parseCssColor(skin.fgCss) ?: c.labelPrimary,
                selected = selected == skin.id,
                onClick = { onSelect(skin.id) },
            )
        }
    }
}

@Composable
private fun SkinChip(label: String, bg: Color, accent: Color, fg: Color, selected: Boolean, onClick: () -> Unit) {
    val c = Dsh.colors
    Column(
        Modifier.width(64.dp).clip(Radius.md).clickable(onClick = onClick).padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            Modifier.size(44.dp).clip(Radius.md).background(bg)
                .border(if (selected) 2.dp else Hairline, if (selected) accent else c.borderL2, Radius.md),
            contentAlignment = Alignment.Center,
        ) {
            Text("Aa", style = DshType.xxs12m, color = fg)
            Box(Modifier.align(Alignment.BottomCenter).padding(bottom = 5.dp).size(8.dp).circleBg(accent))
        }
        Spacer(Modifier.height(4.dp))
        Text(
            label,
            style = DshType.tiny10,
            color = if (selected) c.labelPrimary else c.labelSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
