# 🚀 Apache Kafka – Phase 1 (Foundations)

This document covers **Kafka fundamentals**.  
After reading this phase, a developer should be able to:
- Understand **what Kafka is**
- Know **why Kafka is used**
- Set up Kafka locally
- Send & consume messages using CLI
- Understand how Kafka fits in backend systems

---

## 📌 What is Apache Kafka?

Apache Kafka is a **distributed event streaming platform**.

In simple words:
> Kafka is a system that helps **multiple backend services communicate with each other asynchronously** using events (messages).

Instead of services calling each other directly, they **send messages to Kafka**, and other services **consume those messages when ready**.

---

## 🤔 Why Kafka?

In modern backend systems, one action often triggers many operations.

Example: **Order Placed**
- Update inventory
- Send email
- Generate invoice
- Update analytics

### ❌ Without Kafka
- Services call each other directly
- If one service is slow or down → whole flow fails
- Tight coupling

### ✅ With Kafka
- Order service sends event → Kafka
- Other services consume independently
- No service dependency
- High scalability and reliability

---

## 🧱 Core Kafka Components

### 1️⃣ Producer
- Sends messages to Kafka
- Example: Order Service

### 2️⃣ Consumer
- Reads messages from Kafka
- Example: Inventory Service, Email Service

### 3️⃣ Topic
- Logical category where messages are stored
- Example:
    - `orders`
    - `payments`
    - `notifications`

### 4️⃣ Partition
- A topic is divided into partitions
- Helps with:
    - Parallel processing
    - High throughput

Topic: orders
├── Partition 0
├── Partition 1
└── Partition 2


### 5️⃣ Offset
- Each message has a position number inside a partition
- Kafka tracks which offset a consumer has read

---

## 🖥 Kafka Broker & Cluster

### Kafka Broker
- A **Kafka server**
- Responsible for:
  - Storing messages
  - Handling producers and consumers
  - Managing partitions

### Kafka Cluster
- Multiple brokers working together
- Provides:
  - Fault tolerance
  - High availability
  - Scalability

---

## 🔄 Kafka as Middle Layer

Kafka acts as a **middle layer between backend services**.



Order Service ──▶ Kafka ──▶ Inventory Service
└──▶ Email Service
└──▶ Analytics Service


Benefits:
- Loose coupling
- Asynchronous processing
- Messages are not lost if consumer is down

---

## ⚙️ Local Kafka Setup (Windows – KRaft Mode)

### Prerequisites
- Java 17+

Check:
```bash
java -version

Download Kafka

Download from Apache Kafka official website

Extract to:

C:\kafka

Start Kafka (First Time Only)
cd C:\kafka
bin\windows\kafka-storage.bat format -t random-uuid -c config\kraft\server.properties

Start Kafka Broker
bin\windows\kafka-server-start.bat config\kraft\server.properties

🧪 Kafka CLI Hands-On
Create Topic
bin\windows\kafka-topics.bat --create \
--topic orders \
--bootstrap-server localhost:9092 \
--partitions 3 \
--replication-factor 1

List Topics
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

Produce Messages
bin\windows\kafka-console-producer.bat \
--topic orders \
--bootstrap-server localhost:9092


Type:

Order 1 created
Order 2 created

Consume Messages
bin\windows\kafka-console-consumer.bat \
--topic orders \
--from-beginning \
--bootstrap-server localhost:9092

🧠 Key Learnings from Phase 1

Kafka stores messages on disk

Messages are durable

Consumers can replay messages

Kafka decouples services

Multiple consumers can read the same message