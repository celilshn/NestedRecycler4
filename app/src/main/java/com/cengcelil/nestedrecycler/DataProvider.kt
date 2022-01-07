package com.cengcelil.nestedrecycler

object DataProvider {
    fun getParentItems(): ArrayList<ParentItem> {
        return arrayListOf(
            ParentItem(
                childList = arrayListOf(
                    ChildItem(name = "Ahmet"),
                    ChildItem(name = "Mehmet"),
                    ChildItem(name = "Dursun")
                ),
                className = "A1"
            ),
            ParentItem(
                childList = arrayListOf(
                    ChildItem(name = "Ahmet"),
                    ChildItem(name = "Mehmet"),
                    ChildItem(name = "Dursun")
                ),
                className = "B1"
            ), ParentItem(
                childList = arrayListOf(
                    ChildItem(name = "Ahmet"),
                    ChildItem(name = "Mehmet"),
                    ChildItem(name = "Dursun")
                ),
                className = "C1"
            ), ParentItem(
                childList = arrayListOf(
                    ChildItem(name = "Ahmet"),
                    ChildItem(name = "Mehmet"),
                    ChildItem(name = "Dursun")
                ),
                className = "D1"
            ), ParentItem(
                childList = arrayListOf(
                    ChildItem(name = "Ahmet"),
                    ChildItem(name = "Mehmet"),
                    ChildItem(name = "Dursun")
                ),
                className = "E1"
            )
        )
    }
}