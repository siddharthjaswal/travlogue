package com.sid.domain.repository.gemini

object GeminiConstants {
    // --- Gemini Chat Model (General Text Generation) ---
    // System Instructions
    const val SYS_INSTRUCTION_PHASE_ONE = """
You are 'Atlas', a friendly and enthusiastic AI travel assistant.

Your first job is to understand the user's core request. Identify two things: the **destination** and the **duration** of the trip.

Based on what's missing, ask a single, clear, and friendly question to get the next piece of information. Do not suggest activities or create a plan yet.
"""

    // Logging - General Chat
    const val LOG_CHAT_HISTORY_BUILDING = "Building chat history for Gemini. History size: %d"
    const val LOG_CHAT_HISTORY_MAPPED_SIZE = "Mapped chat history size: %d"
    const val LOG_CHAT_STARTING_WITH_HISTORY = "Starting chat with history. Mapped history size: %d"
    const val LOG_CHAT_PROMPT_SENDING_STREAM = "Sending prompt to Gemini via stream: %s"


    // System Instructions
    const val SYS_INSTRUCTION_TRIP_JSON_NAME_FORMAT = "Add Destination Related Cute Emoji to the name field. Keep it maximum to 3 words and a emoji. Come up with cute names"

    const val SYS_INSTRUCTION_DAY_JSON_NAME_FORMAT = ""


    // Logging - Trip JSON Processing
    const val LOG_TRIP_JSON_RESPONSE_RECEIVED = "Received Trip JSON response from Gemini: %s"
    const val LOG_TRIP_JSON_PARSING_ATTEMPT = "Attempting to parse Trip JSON"
    const val LOG_TRIP_JSON_PARSE_SUCCESS = "Successfully parsed Trip JSON to TripEntity: %s"
    const val LOG_TRIP_JSON_PARSE_ERROR = "Error parsing Trip JSON"
    const val LOG_TRIP_JSON_RESPONSE_EMPTY = "Received empty or null Trip JSON response from Gemini"


    // --- Imagen Model (Banner Image Generation) ---
    // Model Name
    const val IMAGEN_MODEL_NAME_BANNER = "imagen-3.0-generate-002" // Clarified it's for banner

    // Logging - Banner Generation
    const val LOG_IMAGEN_MODEL_INITIALIZING = "Initializing Imagen model: %s"
    const val LOG_BANNER_GENERATING_WITH_PROMPT = "Generating banner image with prompt: %s"
    const val LOG_BANNER_GENERATION_SUCCESS_OBJECT = "Successfully generated banner image object for prompt: %s"
    const val LOG_BANNER_RESPONSE_NO_IMAGES = "No images returned from Imagen for prompt: %s"
    const val LOG_BANNER_GENERATION_ERROR = "Error generating banner image for prompt: %s" // Added colon for consistency
    const val LOG_BANNER_BITMAP_CONVERTED = "Banner image converted to bitmap for prompt: %s"
    const val LOG_BANNER_IMAGE_SAVE_DELEGATING = "Delegating image save for prompt: %s"
}