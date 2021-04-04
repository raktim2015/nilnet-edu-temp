package in.nilnet.nilneteducation.viewmodels;

/*
    Singleton class to load data from firebase after authentication
*/


public class MainViewModel {

    private static MainViewModel mainViewModel = null;


    MainViewModel() {
    }
    public static MainViewModel getInstance() {
        if(mainViewModel == null) {
            mainViewModel = new MainViewModel();
        }
        return mainViewModel;
    }

}
