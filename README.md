# 🌿 GREEN REV — Global AI Civic Intelligence & Public Health Safeguarding Platform

> **Live Prototype Link:** [https://green-rev-645452062350.asia-south1.run.app/](https://green-rev-645452062350.asia-south1.run.app/)

---

## 📌 Project Overview

**GREEN REV** ek unified, multi-pillar AI ecosystem hai jo modern smart cities ke do sabse critical problem areas ko address karta hai:
1. **Hyper-Local Civic Infrastructure & Public Transit Hazards:** Anonymous citizen reporting with AI-driven severity indexing and auto-dispatch directives.
2. **Public Health Safeguarding & Behavioral Accountability:** Transparent vendor substance logging (Alcohol, Vapes, Cigarettes) linked to encrypted Family IDs (`Fam ID`) to prevent health risks and secondary hazards like drunk driving.

---

## 🚀 Key Feature Pillars

### 1. 🍃 Neighborhood Care (Civic & Nature)
* **Real-World GPS Mapping & Dynamic Analytics:** User ki current city aur exact lane location (e.g., *Lane-42, Sector-7*) ko dynamically detect karta hai.
* **26 Civic Categories:** Potholes, garbage dumping, blocked emergency routes, dark lanes, etc.
* **AI-Powered Severity Indexing:** **RevAI Multi-Modal Vision Engine** image evidence scan karke instant severity evaluate karta hai.
* **Smart Aggregation & Budget Estimation:** Same category ke multiple reports milne par graph count auto-increase hota hai, aur AI solution directives ke saath estimated municipal budget calculate karta hai.
* **100% Anonymous Reporting:** Citizen privacy completely protected rehti hai.

### 2. ⚡ Public Hubs (Hierarchical Transit Pulse)
* **Hierarchical Search Structure:** Multi-level transit navigation across `Country ➔ State ➔ City ➔ Terminal` (Airports, Railway Stations, Metro Hubs).
* **City-Based Dynamic Graphs:** User ki current selected city ke according real-time hazard analytics charts auto-load hote hain.
* **Broadcast Safety Directives:** High-footfall terminal hazard alerts for immediate administrative action.

### 3. 🛡️ Health Guardian & Vendor Terminal (Family Shield)
* **Non-Anonymous Substance Logging:** Transactions are directly mapped to a real person's **User ID** and encrypted **Family ID (Fam ID)**.
* **Instant Family Alarming Popups:** Vendor side se Alcohol/Vape/Cigarettes log hone par family portal par instant alarming popups aur real-time notifications trigger hote hain.
* **Date-Wise Health Assessment:** **RevAI Engine** past aur recent purchases ko calculate karke physical aur mental health reports generate karta hai.
* **Drunk Driving & Hazard Prevention:** Real-time timestamp tracking ke basis par drunk driving risks identify kiye jaate hain, jo family intervention aur self-correction nudges mein help karta hai.

---

## 🏗️ Production Architecture

```text
+-----------------------------------------------------------------------------------+
|                            CITIZEN & VENDOR INTERFACE (PWA)                       |
|   Google Maps API (Auto-Lane Detection) | Chart.js City Analytics | i18next       |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
|                        GOOGLE CLOUD RUN & API GATEWAY                             |
|   Spring Boot Containerized Microservices (asia-south1) | OAuth 2.0 / GeoIP     |
+-----------------------------------------------------------------------------------+
                                          |
        +---------------------------------+---------------------------------+
        |                                 |                                 |
        v                                 v                                 v
+-----------------------+     +-----------------------+     +-----------------------+
|   NEIGHBORHOOD CARE   |     |      PUBLIC HUBS      |     |    HEALTH GUARDIAN    |
| Google Maps GIS API   | <-> | Country->State->City  | <-> | Encrypted Fam ID      |
| Gemini Vision AI Scan |     | Redis Graph Cache     |     | Firestore Ledger      |
+-----------------------+     +-----------------------+     +-----------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
|                        DISPATCH & GUARDIAN ACTION NETWORK                         |
|   Geo-fenced Command Auto-Dispatch | Encrypted Family Push Notifications          |
+-----------------------------------------------------------------------------------+
