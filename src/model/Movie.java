package model;

public enum Movie {
    TOP_GUN_2("Top Gun 2"),
    RATATOUILLE("Ratatouille"),
    AVATAR_2("Avatar 2"),
    DUNE_2("Dune 2"),
    BARBIE("Barbie");

    private final String displayName;

    Movie(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static void showMovies() {
        Movie[] movies = Movie.values();
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i].getDisplayName());
        }
    }

    public static String getMovieByOption(int option) {
        Movie[] movies = Movie.values();
        if (option >= 1 && option <= movies.length) {
            return movies[option - 1].getDisplayName();
        }
        return null;
    }
}