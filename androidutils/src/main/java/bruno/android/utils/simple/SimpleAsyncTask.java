package bruno.android.utils.simple;

public abstract class SimpleAsyncTask<Params, Progress, Result> extends
		android.os.AsyncTask<Params, Progress, Result> {

	@Override
	protected Result doInBackground(Params... params) {
		try {
			return onBackground(params);
		} catch (Exception e) {
			return onError(e);
		}
	}

	protected abstract Result onBackground(Params... params);

	protected Result onError(Exception exception){
		return null;
	}

}
