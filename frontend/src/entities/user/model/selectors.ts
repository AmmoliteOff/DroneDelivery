export const isAuthSelector = (state: RootState) => state.user.isAuth;

export const userSelector = (state: RootState) => state.user.user;

export const userIsLoadingSelector = (state: RootState) => state.user.isLoading;
