package io.github.jackwener.dsh.core.designsystem

import androidx.compose.ui.graphics.Color

/**
 * dream-skin → DshColors 的映射。
 *
 * dream-skin 在网页版里把上面每个 `--dsw-*` token 以内联 CSS 变量的形式写到 `<body>` 上；
 * 这里做的是同一件事：把皮肤 token 覆盖到内置的 [LightDsh] / [DarkDsh] 调色板上。
 * 没有 token 的字段（deepDiving、kind*、labelDimmed 等）保留基础配色。
 */
fun DshColors.withSkin(skin: DshSkin.Preset): DshColors {
    fun p(token: String, fallback: Color): Color = DshSkin.parseCssColor(skin.tokens[token]) ?: fallback
    return copy(
        // labels
        labelPrimary = p("--dsw-alias-label-primary", labelPrimary),
        labelSecondary = p("--dsw-alias-label-secondary", labelSecondary),
        labelTertiary = p("--dsw-alias-label-tertiary", labelTertiary),
        labelOnPrimary = p("--dsw-alias-brand-text", labelOnPrimary),
        link = p("--dsw-alias-brand-primary", link),
        // surfaces
        bgBase = p("--dsw-alias-bg-base", bgBase),
        bgLayer1 = p("--dsw-alias-bg-layer-1", bgLayer1),
        bgLayer2 = p("--dsw-alias-bg-layer-2", bgLayer2),
        bgLayer3 = p("--dsw-alias-bg-layer-3", bgLayer3),
        bgModule = p("--dsw-alias-bg-module-platform", bgModule),
        bgSkeleton = p("--dsw-alias-bg-skeleton", bgSkeleton),
        sidebar = p("--dsw-specific-sidebar-fill", sidebar),
        sidebarActive = p("--dsw-specific-sidebar-nav-item-active", sidebarActive),
        bubble = p("--dsw-specific-bubble", bubble),
        inputMajor = p("--dsw-specific-input-major", inputMajor),
        selector = p("--dsw-specific-selector", selector),
        codeBlock = p("--dsw-alias-markdown-code-block", codeBlock),
        codeBanner = p("--dsw-alias-bg-module-platform", codeBanner),
        inlineCode = p("--dsw-alias-markdown-inline-code", inlineCode),
        menuFill = p("--dsw-menu-surface-fill", menuFill),
        toastBg = p("--dsw-specific-tip", toastBg),
        // borders
        borderL1 = p("--dsw-alias-border-l1", borderL1),
        borderL2 = p("--dsw-alias-border-l2", borderL2),
        borderL3 = p("--dsw-alias-border-l3", borderL3),
        borderL4 = p("--dsw-alias-border-l4", borderL4),
        // brand / buttons / interaction
        brandPrimary = p("--dsw-alias-brand-primary", brandPrimary),
        buttonPrimaryDimmed = p("--dsw-alias-button-primary-dimmed", buttonPrimaryDimmed),
        info = p("--dsw-alias-brand-primary", info),
        floating = p("--dsw-alias-button-elevated-fill", floating),
        hover = p("--dsw-alias-interactive-bg-hover", hover),
        pressed = p("--dsw-alias-interactive-bg-active", pressed),
        hoverDanger = p("--dsw-alias-interactive-bg-hover-danger", hoverDanger),
        business = p("--dsw-alias-state-business-primary", business),
        businessTertiary = p("--dsw-alias-state-business-tertiary", businessTertiary),
        // status
        error = p("--dsw-alias-state-error-primary", error),
        success = p("--dsw-alias-state-success-primary", success),
        warn = p("--dsw-alias-state-warn-primary", warn),
        diffAdded = p("--dsw-alias-file-diff-added-bg", diffAdded),
        diffDeleted = p("--dsw-alias-file-diff-deleted-bg", diffDeleted),
        scrim = p("--dsw-alias-bg-mask-drop", scrim),
    )
}
