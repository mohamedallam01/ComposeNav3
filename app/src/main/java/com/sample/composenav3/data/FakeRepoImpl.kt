package com.sample.composenav3.data

class FakeRepoImpl : FakeRepo {

    private val items = listOf(
        Item(
            id = "1",
            title = "The Shawshank Redemption",
            description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency. Directed by Frank Darabont, 1994. Drama.",
        ),
        Item(
            id = "2",
            title = "The Dark Knight",
            description = "Batman raises the stakes in his war on crime, facing the Joker, a criminal mastermind who plunges Gotham into anarchy. Directed by Christopher Nolan, 2008. Action."
        ),
        Item(
            id = "3",
            title = "Inception",
            description = "A skilled thief who steals secrets from people's dreams is given a chance to have his criminal record erased if he can plant an idea in someone's mind. Directed by Christopher Nolan, 2010. Sci-Fi."
        ),
        Item(
            id = "4",
            title = "Interstellar",
            description = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival as Earth becomes uninhabitable. Directed by Christopher Nolan, 2014. Sci-Fi."
        ),
        Item(
            id = "5",
            title = "The Godfather",
            description = "The aging patriarch of an organized crime dynasty transfers control of his empire to his reluctant youngest son. Directed by Francis Ford Coppola, 1972. Crime."
        ),
        Item(
            id = "6",
            title = "Pulp Fiction",
            description = "The lives of two mob hitmen, a boxer, a gangster and his wife intertwine in four tales of violence and redemption. Directed by Quentin Tarantino, 1994. Crime."
        ),
        Item(
            id = "7",
            title = "Forrest Gump",
            description = "The story of a man with a low IQ who accomplishes great things in his life, witnessing and influencing key historical events along the way. Directed by Robert Zemeckis, 1994. Drama."
        ),
        Item(
            id = "8",
            title = "The Matrix",
            description = "A computer hacker discovers that reality as he knows it is a simulation created by machines, and joins a rebellion to free humanity. Directed by the Wachowskis, 1999. Sci-Fi."
        ),
        Item(
            id = "9",
            title = "Gladiator",
            description = "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery. Directed by Ridley Scott, 2000. Action."
        ),
        Item(
            id = "10",
            title = "The Lord of the Rings: The Fellowship of the Ring",
            description = "A young hobbit embarks on an epic quest to destroy a powerful ring and save Middle-earth from the Dark Lord Sauron. Directed by Peter Jackson, 2001. Fantasy."
        )
    )

    override fun getItems(): List<Item> = items

    override fun getItemById(id: String): Item? = items.find { it.id == id }
}
