package com.example.Assignment_3.config;

import com.example.Assignment_3.model.Movie;
import com.example.Assignment_3.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(MovieRepository movieRepository, JdbcTemplate jdbcTemplate) {
        return args -> {
            // Clear existing data
            movieRepository.deleteAll();
            
            // Reset auto-increment sequence to start from 1
            jdbcTemplate.execute("ALTER TABLE movies AUTO_INCREMENT = 1");
            
            // Create and save 20 movies
            Movie[] movies = {
                new Movie("The Shawshank Redemption", "Castle Rock Entertainment", "Drama", "Frank Darabont", "Frank Darabont", "Tim Robbins, Morgan Freeman", 1994, 142, "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "R", "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_.jpg", 9.3),
                new Movie("The Godfather", "Paramount Pictures", "Crime", "Francis Ford Coppola", "Mario Puzo, Francis Ford Coppola", "Marlon Brando, Al Pacino", 1972, 175, "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "R", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg", 9.2),
                new Movie("The Dark Knight", "Warner Bros.", "Action", "Christopher Nolan", "Jonathan Nolan, Christopher Nolan", "Christian Bale, Heath Ledger", 2008, 152, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "PG-13", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg", 9.0),
                new Movie("Pulp Fiction", "Miramax", "Crime", "Quentin Tarantino", "Quentin Tarantino, Roger Avary", "John Travolta, Samuel L. Jackson", 1994, 154, "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", "R", "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg", 8.9),
                new Movie("Fight Club", "20th Century Fox", "Drama", "David Fincher", "Chuck Palahniuk, Jim Uhls", "Brad Pitt, Edward Norton", 1999, 139, "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.", "R", "https://m.media-amazon.com/images/M/MV5BNDIzNDU0YzEtYzE5Ni00ZjlkLTk5ZjgtNjM3NWE4YzA3Nzk3XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_.jpg", 8.8),
                new Movie("Inception", "Warner Bros.", "Sci-Fi", "Christopher Nolan", "Christopher Nolan", "Leonardo DiCaprio, Joseph Gordon-Levitt", 2010, 148, "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "PG-13", "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg", 8.8),
                new Movie("The Matrix", "Warner Bros.", "Sci-Fi", "Lana Wachowski, Lilly Wachowski", "Lana Wachowski, Lilly Wachowski", "Keanu Reeves, Laurence Fishburne", 1999, 136, "A computer programmer discovers that reality as he knows it is a simulation created by machines, and joins a rebellion to break free.", "R", "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg", 8.7),
                new Movie("Goodfellas", "Warner Bros.", "Crime", "Martin Scorsese", "Nicholas Pileggi, Martin Scorsese", "Robert De Niro, Ray Liotta", 1990, 146, "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito.", "R", "https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDgtN2RjYy00YzM1LWI4ZmQtMjIwYjFjNmI3ZGEwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg", 8.7),
                new Movie("The Silence of the Lambs", "Orion Pictures", "Thriller", "Jonathan Demme", "Thomas Harris, Ted Tally", "Jodie Foster, Anthony Hopkins", 1991, 118, "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.", "R", "https://m.media-amazon.com/images/I/817d-ZS5GcL._AC_SY879_.jpg", 8.6),
                new Movie("Interstellar", "Paramount Pictures", "Sci-Fi", "Christopher Nolan", "Jonathan Nolan, Christopher Nolan", "Matthew McConaughey, Anne Hathaway", 2014, 169, "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", "PG-13", "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg", 8.6),
                new Movie("The Departed", "Warner Bros.", "Crime", "Martin Scorsese", "William Monahan", "Leonardo DiCaprio, Matt Damon", 2006, 151, "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.", "R", "https://m.media-amazon.com/images/M/MV5BMTI1MTY2OTIxNV5BMl5BanBnXkFtZTYwNjQ4NjY3._V1_.jpg", 8.5),
                new Movie("The Prestige", "Touchstone Pictures", "Drama", "Christopher Nolan", "Christopher Nolan, Jonathan Nolan", "Christian Bale, Hugh Jackman", 2006, 130, "After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other.", "PG-13", "https://m.media-amazon.com/images/I/71OCd9tb0vL._AC_SX679_.jpg", 8.5),
                new Movie("The Lion King", "Walt Disney Pictures", "Animation", "Roger Allers, Rob Minkoff", "Irene Mecchi, Jonathan Roberts", "Matthew Broderick, James Earl Jones", 1994, 88, "Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.", "G", "https://m.media-amazon.com/images/I/81UKRDNKrML._AC_SY879_.jpg", 8.5),
                new Movie("Gladiator", "DreamWorks", "Action", "Ridley Scott", "David Franzoni, John Logan", "Russell Crowe, Joaquin Phoenix", 2000, 155, "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.", "R", "https://m.media-amazon.com/images/I/7192AdLeVGL._AC_SY879_.jpg", 8.5),
                new Movie("American History X", "New Line Cinema", "Drama", "Tony Kaye", "David McKenna", "Edward Norton, Edward Furlong", 1998, 119, "A former neo-nazi skinhead tries to prevent his younger brother from going down the same wrong path that he did.", "R", "https://m.media-amazon.com/images/M/MV5BZjA0MTM4MTQtNzY5MC00NzY3LWI1ZTgtYzcxMjkyMzU4MDZiXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_.jpg", 8.5),
                new Movie("The Green Mile", "Warner Bros.", "Drama", "Frank Darabont", "Frank Darabont", "Tom Hanks, Michael Clarke Duncan", 1999, 189, "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.", "R", "https://m.media-amazon.com/images/I/61ZcVjgsdvL._AC_UL640_FMwebp_QL65_.jpg", 8.6),
                new Movie("The Usual Suspects", "PolyGram Filmed Entertainment", "Crime", "Bryan Singer", "Christopher McQuarrie", "Kevin Spacey, Gabriel Byrne", 1995, 106, "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup.", "R", "https://m.media-amazon.com/images/M/MV5BYTViNjMyNmUtNDFkNC00ZDRlLThmMDUtZDU2YWE4NGI2ZjVmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg", 8.5),
                new Movie("Se7en", "New Line Cinema", "Crime", "David Fincher", "Andrew Kevin Walker", "Morgan Freeman, Brad Pitt", 1995, 127, "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.", "R", "https://m.media-amazon.com/images/I/71ivyTtPwoL.__AC_SX300_SY300_QL70_ML2_.jpg", 8.6),
                new Movie("The Shining", "Warner Bros.", "Horror", "Stanley Kubrick", "Stephen King, Stanley Kubrick", "Jack Nicholson, Shelley Duvall", 1980, 146, "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.", "R", "https://m.media-amazon.com/images/M/MV5BZWFlYmY2MGEtZjVkYS00YzU4LTg0YjQtYzY1ZGE3NTA5NGQxXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg", 8.4),
                new Movie("The Grand Budapest Hotel", "Fox Searchlight Pictures", "Comedy", "Wes Anderson", "Wes Anderson, Hugo Guinness", "Ralph Fiennes, Tony Revolori", 2014, 99, "A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the hotel's glorious years under an exceptional concierge.", "R", "https://m.media-amazon.com/images/I/71upKXXjMsL._AC_UL640_FMwebp_QL65_.jpg", 8.1)
            };
            
            movieRepository.saveAll(Arrays.asList(movies));
            System.out.println("Database initialized with 20 movies!");
        };
    }
} 