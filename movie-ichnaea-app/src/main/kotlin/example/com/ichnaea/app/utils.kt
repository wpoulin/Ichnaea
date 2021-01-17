package example.com.ichnaea.app

import example.com.ichnaea.data.IchnaeaDal

fun setupInitialData(dal: IchnaeaDal) {
    val war = dal.createGenre("War")
    val biography = dal.createGenre("Biography")
    val action = dal.createGenre("Action")
    val thriller = dal.createGenre("Thriller")

    val hacksawRidge = dal.createShow(
            "Hacksaw Ridge",
            "World War II American Army Medic Desmond T. Doss, who served during the Battle of Okinawa, refuses to kill people, and becomes the first man in American history to receive the Medal of Honor without firing a shot.",
            2016,
            139
    )

    val bourneSupremacy = dal.createShow(
            "The Bourne Supremacy",
            "When Jason Bourne is framed for a CIA operation gone awry, he is forced to resume his former life as a trained assassin to survive.",
            2004,
            108
    )

    if (war != null && biography != null && action != null && thriller != null) {
        if (hacksawRidge != null && bourneSupremacy != null) {
            dal.addGenreToShow(hacksawRidge, war)
            dal.addGenreToShow(hacksawRidge, biography)
            dal.addGenreToShow(bourneSupremacy, action)
            dal.addGenreToShow(bourneSupremacy, thriller)
        }
    }
}