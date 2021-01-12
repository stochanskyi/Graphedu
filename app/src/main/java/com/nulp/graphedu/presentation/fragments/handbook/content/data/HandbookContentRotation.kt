package com.nulp.graphedu.presentation.fragments.handbook.content.data

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.handbook.content.component.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class HandbookContentRotation : HandbookContent {
    override fun provideComponent(): HandbookContentComponent {
        return HandbookLinearContainer(
            HandbookContentText("  Математичною основою задачі створення рухомих зображень у КГ є афінні перетворення.\n" +
                    "    Афінні перетворення трьох видів є основою для рухомих зображень:\n" +
                    "  - переміщення/зсув;\n" +
                    "  - масштабування (збільшення/зменшення);\n" +
                    "  - поворот на кут.\n" +
                    "\n" +
                    "   Ці перетворення можна проводити як відносно системи координат, так і відносно самого об'єкту зображення."),
            HandbookContentTitle("Поворот на кут φ"),
            HandbookContentText("  Поворот точки відносно початку координат на кут ϕ проти годинникової стрілки (а) задається формулами"),
            HandbookContentImage(R.drawable.rotation_system, true),
            HandbookContentText("  Поворот системи координат на кут ϕ проти годинникової (б) стрілки задається формулами"),
            HandbookContentImage(R.drawable.rotation_system_2, true),
            HandbookContentImage(R.drawable.rotation_system_3, true),
            HandbookContentTitle("Масштабування"),
            HandbookContentText("   Масштабування системи координат задається як зміна одиничних відрізків. Тоді координати точки в новій системі (0X`Y`) будуть визначатися за формулами"),
            HandbookContentImage(R.drawable.rotation_system_4, true)
        )
    }
}