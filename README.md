🚀 Apache Kafka Learning & Implementation (1-Day Intensive Study)
📌 Objective

The objective of this task was:

To understand Apache Kafka from basics

To implement Kafka using Spring Boot

To understand Kafka architecture (Producer, Consumer, Topic, Partition, Offset)

To integrate Kafka with WebSocket for real-time communication

To prepare for production-level understanding

🧠 Phase 1 – Kafka Fundamentals Learned
1️⃣ What is Apache Kafka?

Apache Kafka is a distributed event streaming platform used to build real-time data pipelines and streaming applications.

It allows:

Producers to send messages

Brokers to store messages

Consumers to read messages

Scalable and fault-tolerant communication

2️⃣ Why Kafka?

Traditional REST communication is synchronous and tightly coupled.

Kafka enables:

Asynchronous communication

Loose coupling between services

High throughput

Scalability

3️⃣ Core Concepts Learned
🔹 Producer

Sends messages to Kafka topic.

🔹 Consumer

Reads messages from Kafka topic.

🔹 Broker

Kafka server that stores data.

🔹 Topic

Category where messages are stored.

🔹 Partition

Divides topic into multiple lanes for parallel processing.

🔹 Offset

Unique position number of message inside a partition.

🔹 Consumer Group

Multiple consumers sharing load of partitions.

🏗 Kafka Architecture Understanding

Message Flow:

Producer → Topic → Partition → Consumer
Offset tracks how much data consumer has processed.

Partition enables horizontal scalability.

Ordering is maintained within a partition.

⚙️ Phase 2 – Local Setup Steps
Step 1 – Installed Kafka (KRaft Mode)

Downloaded Kafka binary

Formatted storage

Started Kafka broker

Step 2 – Created Topic

Command used:

kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

Step 3 – Tested Using CLI

Used console producer

Used console consumer

Verified message flow

💻 Phase 3 – Spring Boot Implementation
Dependency Added
spring-kafka

application.yml Configuration
spring:
kafka:
bootstrap-servers: localhost:9092
consumer:
group-id: my-group
auto-offset-reset: earliest

Kafka Producer Implementation

Used KafkaTemplate to send messages to topic.

Kafka Consumer Implementation

Used @KafkaListener to consume messages.

🔄 Phase 4 – Kafka + WebSocket Integration

Architecture:

Producer → Kafka → Consumer → WebSocket → Client Browser

Kafka handles backend communication.
WebSocket pushes real-time updates to UI.

Use Case Implemented:
Real-time message broadcasting system.

🧪 Key Learnings

Kafka is asynchronous

Partition enables parallel processing

Offset tracks message consumption

Consumer group balances load

Kafka is better than REST for event-driven systems

WebSocket is used for client communication

Kafka is used for service-to-service communication

📈 Real-World Use Cases Studied

Order processing system

Real-time stock updates

Chat systems

Notification services

Microservices communication

⚠️ Challenges Faced

Understanding partition vs consumer

Understanding offset mechanism

Kafka setup configuration

Consumer group behaviour

🎯 Conclusion

Through this implementation, I gained:

Strong understanding of Kafka fundamentals

Practical experience with Spring Boot Kafka

Real-time integration using WebSocket

Production-level architectural understanding

Kafka is suitable for scalable, event-driven systems and high-throughput messaging scenarios.