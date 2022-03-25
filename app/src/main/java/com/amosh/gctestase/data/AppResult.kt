package com.amosh.gctestase.data

sealed class AppResult(
    val status: Status? = null,
    val cars: List<Car>? = null,
) {
    class SuccessWithList(
        status: Status = Status(
            code = 200,
            message = "Success"
        ),
        cars: List<Car>? = listOf(
            Car(
                model = 2021,
                plate_number = "ABC 123",
                brand = "Honda",
                unit_price = 30.5,
                currency = "EGP",
                color = CarColor.RED
            ),
            Car(
                model = 2020,
                plate_number = "EFG 321",
                brand = "Hyundai",
                unit_price = 50.5,
                currency = "EGP",
                color = CarColor.BLUE

            ),
            Car(
                model = 2021,
                plate_number = "XYZ 313",
                brand = "MG",
                unit_price = 15.5,
                currency = "EGP",
                color = CarColor.BLACK
            ),
            Car(
                model = 2022,
                plate_number = "ABC 123",
                brand = "Opel",
                unit_price = 70.5,
                currency = "EGP",
                color = CarColor.WHITE
            )
        ),
    ) :
        AppResult(status, cars)

    class SuccessWithEmptyList(
        status: Status = Status(
            code = 204,
            message = "no cars available"
        ),
        cars: List<Car>? = null,
    ) : AppResult(status, cars)

    class Loading : AppResult()
}