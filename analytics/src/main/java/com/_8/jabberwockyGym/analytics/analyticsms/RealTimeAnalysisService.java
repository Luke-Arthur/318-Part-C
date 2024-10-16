package com._8.jabberwockyGym.analytics.analyticsms;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Getter
@Service
public class RealTimeAnalysisService {

    // Expose the KafkaStreams instance for other services
    private KafkaStreams streams;  // Store the KafkaStreams instance

    @PostConstruct
    public void startStreams() {
        StreamsBuilder builder = new StreamsBuilder();

        // Stream from the booking-created and booking-updated topics
        KGroupedStream<String, String> bookingsStream = builder
                .stream("booking-created", Consumed.with(Serdes.String(), Serdes.String())) // Consume with String key and value
                .groupBy((key, value) -> extractWorkoutClassId(value));  // Group by workout class ID

        // Aggregate the bookings by class ID
        KTable<String, Long> classAvailability = bookingsStream.count(Materialized.as("class-availability-store"));
        classAvailability.toStream().foreach((key, value) -> System.out.println("Class ID: " + key + ", Availability: " + value));

        // Initialize and start the Kafka Streams instance
        streams = new KafkaStreams(builder.build(), getStreamProperties());
        streams.start();
    }

    private String extractWorkoutClassId(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(message);

            // Check if the "classId" field exists
            JsonNode classIdNode = rootNode.get("classId");
            if (classIdNode == null) {
                System.err.println("Error: 'classId' field is missing in the message: " + message);
                return null;
            }

            return classIdNode.asText();  // Get the text value of the "classId" field
        } catch (Exception e) {
            System.err.println("Error extracting workout class ID: " + e.getMessage());
            e.printStackTrace();
            return null;  // Return null if there's an error
        }
    }


    private Properties getStreamProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("application.id", "real-time-analysis-" + System.currentTimeMillis());
        props.put("default.key.serde", Serdes.String().getClass());
        props.put("default.value.serde", Serdes.String().getClass());

        // Set a unique state directory to avoid conflict
        props.put("state.dir", System.getProperty("java.io.tmpdir") + "/kafka-streams/real-time-analysis-" + System.currentTimeMillis());

        return props;
    }

}
