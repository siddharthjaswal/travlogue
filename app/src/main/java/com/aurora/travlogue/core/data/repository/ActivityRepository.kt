package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.data.local.dao.ActivityDao
import com.aurora.travlogue.core.data.local.entities.Activity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val activityDao: ActivityDao
) {

    fun getActivitiesByLocationId(locationId: String): Flow<List<Activity>> {
        return activityDao.getActivitiesByLocationId(locationId)
    }

    suspend fun getActivitiesByLocationIdSync(locationId: String): List<Activity> {
        return activityDao.getActivitiesByLocationIdSync(locationId)
    }

    suspend fun getActivityById(activityId: String): Activity? {
        return activityDao.getActivityById(activityId)
    }

    suspend fun insertActivity(activity: Activity) {
        activityDao.insertActivity(activity)
    }

    suspend fun insertActivities(activities: List<Activity>) {
        activityDao.insertActivities(activities)
    }

    suspend fun updateActivity(activity: Activity) {
        activityDao.updateActivity(activity)
    }

    suspend fun deleteActivity(activity: Activity) {
        activityDao.deleteActivity(activity)
    }

    suspend fun deleteActivityById(activityId: String) {
        activityDao.deleteActivityById(activityId)
    }

    suspend fun deleteActivitiesByLocationId(locationId: String) {
        activityDao.deleteActivitiesByLocationId(locationId)
    }
}
