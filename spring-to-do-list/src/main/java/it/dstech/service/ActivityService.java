package it.dstech.service;


import it.dstech.model.Activity;

public interface ActivityService {
	
	public Activity getActivity (Long id);
	
	public Activity save(Activity activity);
	
	public void delete(Long id);
	
}
