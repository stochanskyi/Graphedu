package com.nulp.graphedu.presentation.fragments.handbook.content.data

import android.os.Parcel
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.handbook.content.component.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class HandbookContentFractal : HandbookContent {
    override fun provideComponent(): HandbookContentComponent {
        return HandbookLinearContainer(
            HandbookContentFormattedText.create("    Слово <b>фрактал</b> утворено від латинського fractus і в перекладі означає той, що складається з фрагментів (подрібнений). Фракталом називаєть- ся структура, що складається з частин, які в якомусь сенсі подібні до цілого.\n" +
                "    Загалом за способом побудови фрактали класифікують на 3 групи: геометричні, алгебраїчні та стохастичні."),
            HandbookContentTitle("Алгебраїчні фрактали"),
            HandbookContentText("    Це найбільша група фракталів. Отримують їх за допомогою нелінійних процесів в n-мірних просторах. Для побудови алгебраїчних фракталів використовуються ітерації нелінійних відобра- жень, що задаються простими алгебра- їчними формулами. Прикладами фракталів цього типу є множина Мандельброта, палаючий корабель, фрактал Ляпунова, басейни Ньютона, біоморфи та інші."),
            HandbookContentTitle("Фрактали Ньютона"),
            HandbookContentText(" Формули для побудови фракталів Ньютона засновані на методі вирішення нелінійних рівнянь, який був придуманий великим математиком ще в XVII столітті."),
            HandbookContentImage(R.drawable.ic_newton_fractal)
        )
    }
}