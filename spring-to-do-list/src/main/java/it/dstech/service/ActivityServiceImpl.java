package it.dstech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dstech.model.Activity;
import it.dstech.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Override
	public Activity getActivity(Long id) {
		return activityRepository.findById(id).get();
	}

	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public void delete(Long id) {
		activityRepository.deleteById(id);
	}

}
