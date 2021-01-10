package com.nulp.graphedu.presentation.fragments.handbook.content.data

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.handbook.content.component.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class HandbookContentColors : HandbookContent {
    override fun provideComponent(): HandbookContentComponent {
        return HandbookLinearContainer(
            HandbookContentFormattedText.create("<b>Колір</b> - це властивість матеріальних об'єктів, яка сприймається як усвідомлене зорове відчуття та виникає в результаті дії на око потоків видимого електронно-магнітного випромі- нювання (з довжинами хвиль від 380 до 760 нм). Саме поняття кольору є особливістю людського \"бачення\" навколишнього середовища."),
            HandbookContentTitle("Колірні моделі"),
            HandbookContentText("Призначення колірної моделі - дати засоби опису кольору в межах деякого колірного діапазону, у тому числі і для виконання інтерполяції кольорів. Існують різні моделі, оскільки із зображенням виконуються різні дії: відображення на екран, видрук на принтер, опрацювання кольорів, перетворення в сірі тони, корекція яскравості, інтенсивності і т.п.\n" +
                    "Кожна модель має своє призначення, тобто ефективна для виконання окремих операцій."),
            HandbookContentTitle("Модель RGB"),
            HandbookContentFormattedText.create("<b>RGB</b> - це апаратно-орієнтована модель, в якій кольори описуються за допомогою змішування  трьох базових кольорів – червоного (Red), зеленого (Green), синього (Blue) – в різних пропорціях."),
            HandbookContentImage(R.drawable.ic_handbook_rgb, true),
            HandbookContentText("Схема адитивних кольорів працює на основі принципу  випромінювання світла. RGB використовується в дисплеях для формування кольорів. У цій схемі відсутністю всіх кольорів є чорний колір, а присутність всіх кольорів - білий."),
            HandbookContentTitle("Модель HSL"),
            HandbookContentFormattedText.create("<b>HSL</b> - колірна модель, в якій будь-який колір визначається трьома характеристиками: кольоровим тоном (англ. Hue), наприклад, синім, червоним, жовтим тощо; насиченістю (англ. Saturation), тобто частиною чистого кольору, без домішки чорної та білої фарб; «світністю» (en; ru) (англ. Lightness), тобто близькістю до білого кольору.")
        )
    }
}