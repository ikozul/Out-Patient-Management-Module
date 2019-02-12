package Repo;

public final class RepoFactory {
    public static IRepo getRepository() {
        return new Repo();
    }
}
