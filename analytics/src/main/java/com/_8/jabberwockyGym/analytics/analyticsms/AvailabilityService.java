package com._8.jabberwockyGym.analytics.analyticsms;

import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    private final RealTimeAnalysisService realTimeAnalysisService;

    @Autowired
    public AvailabilityService(RealTimeAnalysisService realTimeAnalysisService) {
        this.realTimeAnalysisService = realTimeAnalysisService;
    }

    public Long getClassAvailability(String classId) {
        // Access the KafkaStreams instance from RealTimeAnalysisService
        ReadOnlyKeyValueStore<String, Long> store = realTimeAnalysisService.getStreams().store(
                StoreQueryParameters.fromNameAndType("class-availability-store", QueryableStoreTypes.keyValueStore()));

        // Get availability count for the provided classId from the KTable (state store)
        Long availability = store.get(classId);

        // Log or return the availability for further usage
        System.out.println("Class ID: " + classId + ", Availability: " + availability);
        return availability;
    }
}
