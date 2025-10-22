# Transit API Research & Options

**Research Date:** January 2025
**For:** Travlogue v1.0 - Transit Suggestions Feature
**Purpose:** Evaluate API options for suggesting transit options (flights, trains, buses) for detected gaps

---

## 🎯 Feature Goal

When the app detects a gap (location jump without transit), suggest transit options:
- 🚂 Train: $45, 2h 30m, Provider X
- ✈️ Flight: $120, 1h 15m, Airline Y
- 🚌 Bus: $25, 4h 00m, Company Z

---

## 💰 API Options Comparison

| API | Free Tier | Paid Pricing | Coverage | Best For |
|-----|-----------|--------------|----------|----------|
| **Google Routes API** | Free monthly calls per SKU | $5-15 per 1,000 requests | Global, multi-modal | Comprehensive routing |
| **Amadeus API** | Free monthly quota | Pay per additional request | 400+ airlines, 90+ trains | Flights + trains with pricing |
| **Skyscanner API** | 50-100 req/min (approved partners) | Partner-only | Flights only | Flight comparison |
| **OpenTripPlanner** | ✅ **100% FREE** (self-hosted) | Server hosting (~$10-20/mo) | Multi-modal (GTFS data) | Full control, privacy |
| **Transit APIs** | Free with capped calls | Unknown | 300 cities, 16 countries | Public transit |

---

## 📋 Detailed API Analysis

### 1. Google Routes API (formerly Directions API)

**Status:** Directions API deprecated March 1, 2025 → Migrated to Routes API

**Pricing Tiers:**
- **Basic:** $5 per 1,000 requests
- **Advanced:** $10 per 1,000 requests (real-time traffic, 11-25 waypoints)
- **Preferred:** $15 per 1,000 requests (toll calculation, advanced features)

**Free Tier:**
- Replaced previous $200/month credit with free monthly calls per SKU (exact limits not publicly specified)

**Transit-Specific Limits:**
- Maximum 100 elements per ComputeRouteMatrix request with travelMode=TRANSIT
- (Standard limit for other modes: 625 elements)

**Pros:**
- ✅ Global coverage, high reliability
- ✅ Real-time traffic and transit data
- ✅ Well-documented, mature API
- ✅ Easy integration with Google services
- ✅ Free monthly quota available

**Cons:**
- ❌ Can get expensive at scale ($5-15 per 1K requests)
- ❌ Transit requests have lower limits (100 vs 625 elements)
- ❌ Requires Google Cloud account setup
- ❌ Subject to Google's pricing changes

**Cost Example:**
- 1,000 users/month × 5 searches each = 5,000 requests
- 5,000 × $0.005 (Basic tier) = **$25/month**
- At 10,000 requests: **$50/month**

**Documentation:**
- https://developers.google.com/maps/documentation/routes/usage-and-billing

---

### 2. Amadeus API

**Status:** Active, requires application/approval

**Coverage:**
- **Flights:** 400+ airlines, 130 low-cost carriers, 150 ancillary services
- **Trains:** 90+ train companies
- **Features:** Flight price monitoring, CO2 footprint data

**Pricing:**
- Free request quota in test environment
- Free request quota maintained in production
- Pay only for additional calls beyond quota
- **Note:** Exact free tier limits not publicly disclosed

**Pros:**
- ✅ Free quota in both test AND production
- ✅ Excellent flight coverage (400+ airlines)
- ✅ Train booking API included
- ✅ Real pricing data built-in
- ✅ Enterprise-grade reliability

**Cons:**
- ❌ Requires application/approval process
- ❌ Free tier limits not clearly published
- ❌ Less coverage for buses/local public transit
- ❌ Primarily focused on commercial travel

**Best Use Case:**
- Flight and train booking with real pricing
- Commercial/intercity travel routes
- Apps targeting business travelers

**Documentation:**
- https://developers.amadeus.com/pricing
- https://developers.amadeus.com/self-service/category/flights

---

### 3. Skyscanner API

**Status:** Partner-only, requires approval (2-week review)

**Access:**
- Free for approved non-commercial use
- Businesses must demonstrate viable use case and audience
- Not openly available to all developers

**Rate Limits (Free Tier via RapidAPI):**
- 50 requests per minute overall limit
- Unlimited requests and query sessions
- Direct API: Up to 100 calls/minute (live rates), 500/minute (cached)

**Pros:**
- ✅ Free for approved non-commercial partners
- ✅ Popular flight comparison data
- ✅ 50-100 requests/minute rate limit
- ✅ Good for flight price comparison

**Cons:**
- ❌ **Requires approval** (not guaranteed)
- ❌ 2-week application review process
- ❌ Flights only (no trains/buses)
- ❌ Must prove business viability
- ❌ May reject applications

**Risk Assessment:**
- **High risk:** Not guaranteed approval for new/small apps
- **Medium effort:** 2-week wait for approval decision

**Documentation:**
- https://www.partners.skyscanner.net/product/travel-api

---

### 4. OpenTripPlanner (OTP) ⭐ **RECOMMENDED FOR MVP**

**Status:** Open source, 16+ years of development

**Cost:**
- **API Calls:** $0 (unlimited, self-hosted)
- **Hosting:** $5-20/month (VPS) or free tier (Railway/Fly.io initially)

**Technology:**
- Open source multi-modal trip planner
- Uses open data: GTFS (transit schedules) + OpenStreetMap
- Java-based server with GraphQL APIs
- Can be containerized with Docker

**Coverage:**
- Multi-modal: transit, biking, walking, bike share, ride hailing
- Works with any region that publishes GTFS data
- Global coverage (where GTFS data available)

**Pros:**
- ✅ **100% free API calls** (no per-request costs)
- ✅ Multi-modal routing (train, bus, bike, walk, ride-share)
- ✅ Full control over data and features
- ✅ Privacy-friendly (self-hosted, no third-party tracking)
- ✅ No rate limits (you control the server)
- ✅ No application/approval required
- ✅ Mature project (16+ years)
- ✅ Active community support

**Cons:**
- ❌ Requires self-hosting (server management)
- ❌ Need to maintain GTFS data feeds (periodic updates)
- ❌ No built-in live flight pricing (routing only)
- ❌ More initial setup complexity
- ❌ Server costs ($10-20/month for production)

**Cost Example:**
- DigitalOcean Droplet: $12/month (2GB RAM)
- Railway.app: $5-10/month (usage-based)
- AWS Lightsail: $10/month
- **Fly.io/Railway free tier:** $0 initially (with limits)

**Data Sources:**
- GTFS feeds (free, public): https://transitfeeds.com/
- OpenStreetMap (free, open data)
- Updated periodically (monthly/quarterly)

**Use Cases:**
- Transit routing between locations
- Estimated travel times
- Multi-modal journey planning
- Offline-capable (data cached on server)

**Documentation:**
- https://www.opentripplanner.org/
- https://github.com/opentripplanner/OpenTripPlanner

---

### 5. Other Free/Freemium APIs

**Transit APIs (Free with Caps):**
- Coverage: 300 cities, 16 countries
- Multi-modal: trains, buses, bikes
- Free tier with capped API calls
- No flight pricing

**Regional Public Transit APIs:**
- **UK:** Transport API (dataset-based)
- **Norway:** National transport API (all modes + walking)
- **US:** Various city-specific APIs (NYC, SF, etc.)

**Flight-Specific:**
- **adsbdb:** Open access aircraft/airline/route data
- **Aviation APIs:** Various free flight data sources
- Typically no pricing data (routes/schedules only)

---

## 🎯 Recommended Approach

### **Phase 1 (MVP): OpenTripPlanner**
**Timeline:** 1-2 weeks to set up

**Why:**
- ✅ **Zero API costs** - perfect for MVP/testing
- ✅ No approval process - start immediately
- ✅ Real multi-modal routing (train/bus/walk/bike)
- ✅ Uses public GTFS data (free, available for most major cities)
- ✅ Full control, no vendor lock-in

**Implementation Steps:**
1. Set up OTP server using Docker
2. Deploy to Railway.app or Fly.io (free tier initially)
3. Download GTFS feeds for target regions (US, Europe, Asia)
4. Expose GraphQL API
5. Integrate with Travlogue Android app
6. Display routing suggestions (routes, times, modes)

**Limitations to Accept:**
- No live flight prices (show route + "Book on [Provider]" links)
- Requires periodic GTFS data updates
- Small server hosting cost (~$10-20/month at scale)

---

### **Phase 2 (Production): Hybrid Approach**
**Timeline:** After MVP validation

**Strategy:**
1. **OpenTripPlanner** - Primary routing engine (trains, buses, local transit)
2. **Amadeus API** - Flight pricing (when free tier available, after approval)
3. **Google Routes API** - Fallback for missing data (pay-per-use)

**Cost Estimate:**
- Server hosting: $10-20/month
- Amadeus API: $0 (free tier) + overage if needed
- Google Routes API: $10-30/month (occasional use)
- **Total: $20-70/month** for moderate traffic (thousands of users)

---

### **Phase 3 (Scale): Add Real-Time Pricing**
**Timeline:** After user validation, revenue generation

**Options:**
1. **Apply for Amadeus Partnership** - Flight/train pricing
2. **Skyscanner Integration** - Flight comparison (if approved)
3. **Affiliate Links** - Redirect to booking sites (Skyscanner, Kayak, etc.)
4. **Revenue Model** - Commission from bookings to offset API costs

---

## 💡 Implementation Recommendations

### **Option A: Start with OpenTripPlanner (Recommended)**

**Pros:**
- ✅ Free, immediate start
- ✅ Real routing suggestions
- ✅ No approval waiting
- ✅ Learn and iterate quickly

**Timeline:** 1-2 weeks
**Cost:** $0-10/month initially

**Steps:**
1. Set up OTP server with Docker
2. Download GTFS feeds for US/Europe
3. Create Android API client
4. Display suggestions in gap detection UI
5. Add "Book on [Provider]" external links

---

### **Option B: Use Google Routes API**

**Pros:**
- ✅ Easiest integration
- ✅ Most reliable data
- ✅ Quick implementation (2-3 days)

**Cons:**
- ❌ Costs $5-15 per 1K requests
- ❌ Can get expensive quickly

**Timeline:** 2-3 days
**Cost:** $25-100/month (depends on usage)

---

### **Option C: Apply for Amadeus + Wait**

**Pros:**
- ✅ Free if approved
- ✅ Real flight/train pricing

**Cons:**
- ❌ 2-4 week wait
- ❌ No guarantee of approval
- ❌ Delays feature launch

**Timeline:** 2-4 weeks + integration time

---

## 🚀 Immediate Next Steps

### **Recommended Path:**

1. **Week 1-2:** Set up OpenTripPlanner
   - Deploy OTP server on Railway.app (free tier)
   - Load GTFS data for major US cities
   - Test routing queries

2. **Week 3:** Android Integration
   - Create API client for OTP
   - Integrate with gap detection feature
   - Show routing suggestions in UI

3. **Week 4:** UI/UX Polish
   - Display transit options (route, time, mode)
   - Add external booking links
   - Test with real trip data

4. **Parallel:** Apply for Amadeus API
   - Submit application
   - While waiting, MVP works with OTP
   - Migrate to hybrid approach when approved

---

## 📊 Cost Projections

### **MVP (Months 1-3):**
- OpenTripPlanner hosting: $0-10/month
- **Total: $0-10/month**

### **Growth (Months 4-12):**
- OTP hosting: $10-20/month
- Amadeus API: $0 (free tier) + $0-30 overage
- Google Routes fallback: $10-20/month
- **Total: $20-70/month**

### **Scale (Year 2+):**
- Dedicated server: $50-100/month
- API costs: $50-200/month
- CDN/caching: $20-50/month
- **Total: $120-350/month** (with revenue from bookings)

---

## 🔗 Key Resources

### APIs:
- **Google Routes API:** https://developers.google.com/maps/documentation/routes
- **Amadeus API:** https://developers.amadeus.com/
- **Skyscanner API:** https://www.partners.skyscanner.net/
- **OpenTripPlanner:** https://www.opentripplanner.org/

### Data Sources:
- **GTFS Feeds:** https://transitfeeds.com/
- **OpenStreetMap:** https://www.openstreetmap.org/
- **Transit Wiki:** https://www.transitwiki.org/

### Communities:
- **OTP GitHub:** https://github.com/opentripplanner/OpenTripPlanner
- **Mobility Data:** https://github.com/MobilityData/awesome-transit

---

## ✅ Decision Log

**Status:** Research complete, decision pending

**Options:**
1. ⭐ **OpenTripPlanner** - Recommended for MVP (free, immediate)
2. Google Routes API - Fallback for production (reliable, paid)
3. Amadeus API - Future enhancement (real pricing, requires approval)

**Next Action:**
- Discuss with team/stakeholders
- Choose implementation path
- Create technical implementation plan

---

**Last Updated:** January 2025
**Researched By:** Claude Code
**Status:** Ready for implementation decision
