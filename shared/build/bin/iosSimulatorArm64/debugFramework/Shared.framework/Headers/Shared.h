#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class NSLocale, SharedActivities, SharedActivity, SharedActivityQueries, SharedActivityType, SharedBooking, SharedBookingQueries, SharedBookingType, SharedBookings, SharedColorFamily, SharedCreateTripUiEvent, SharedCreateTripUiEventNavigateBack, SharedCreateTripUiEventShowError, SharedCreateTripUiEventTripCreatedSuccess, SharedCreateTripUiState, SharedCreateTripViewModel, SharedDatabaseDriverFactory, SharedDateTimeUtils, SharedDateType, SharedGap, SharedGapDetectionService, SharedGapQueries, SharedGapType, SharedGaps, SharedHomeUiState, SharedHomeViewModel, SharedKoinHelper, SharedKoinHelperCompanion, SharedKoin_coreBeanDefinition<T>, SharedKoin_coreCallbacks<T>, SharedKoin_coreExtensionManager, SharedKoin_coreInstanceFactory<T>, SharedKoin_coreInstanceFactoryCompanion, SharedKoin_coreInstanceRegistry, SharedKoin_coreKind, SharedKoin_coreKoin, SharedKoin_coreKoinDefinition<R>, SharedKoin_coreLevel, SharedKoin_coreLockable, SharedKoin_coreLogger, SharedKoin_coreModule, SharedKoin_coreParametersHolder, SharedKoin_corePropertyRegistry, SharedKoin_coreResolutionContext, SharedKoin_coreScope, SharedKoin_coreScopeDSL, SharedKoin_coreScopeRegistry, SharedKoin_coreScopeRegistryCompanion, SharedKoin_coreSingleInstanceFactory<T>, SharedKotlinAbstractCoroutineContextElement, SharedKotlinAbstractCoroutineContextKey<B, E>, SharedKotlinArray<T>, SharedKotlinByteArray, SharedKotlinByteIterator, SharedKotlinCancellationException, SharedKotlinEnum<E>, SharedKotlinEnumCompanion, SharedKotlinException, SharedKotlinFloatArray, SharedKotlinFloatIterator, SharedKotlinIllegalStateException, SharedKotlinIntArray, SharedKotlinIntIterator, SharedKotlinIntProgression, SharedKotlinIntProgressionCompanion, SharedKotlinIntRange, SharedKotlinIntRangeCompanion, SharedKotlinKTypeProjection, SharedKotlinKTypeProjectionCompanion, SharedKotlinKVariance, SharedKotlinLazyThreadSafetyMode, SharedKotlinMatchGroup, SharedKotlinMatchResultDestructured, SharedKotlinNothing, SharedKotlinPair<__covariant A, __covariant B>, SharedKotlinRegex, SharedKotlinRegexCompanion, SharedKotlinRegexOption, SharedKotlinRuntimeException, SharedKotlinShortArray, SharedKotlinShortIterator, SharedKotlinThrowable, SharedKotlinUnit, SharedKotlinx_coroutines_coreCoroutineDispatcher, SharedKotlinx_coroutines_coreCoroutineDispatcherKey, SharedKotlinx_datetimeDayOfWeek, SharedKotlinx_datetimeDayOfWeekNames, SharedKotlinx_datetimeDayOfWeekNamesCompanion, SharedKotlinx_datetimeInstant, SharedKotlinx_datetimeInstantCompanion, SharedKotlinx_datetimeLocalDate, SharedKotlinx_datetimeLocalDateCompanion, SharedKotlinx_datetimeLocalDateTime, SharedKotlinx_datetimeLocalDateTimeCompanion, SharedKotlinx_datetimeLocalTime, SharedKotlinx_datetimeLocalTimeCompanion, SharedKotlinx_datetimeMonth, SharedKotlinx_datetimeMonthNames, SharedKotlinx_datetimeMonthNamesCompanion, SharedKotlinx_datetimePadding, SharedKotlinx_io_coreBuffer, SharedKotlinx_serialization_coreSerialKind, SharedKotlinx_serialization_coreSerializersModule, SharedKtor_client_coreHttpClient, SharedKtor_client_coreHttpClientCall, SharedKtor_client_coreHttpClientCallCompanion, SharedKtor_client_coreHttpClientConfig<T>, SharedKtor_client_coreHttpClientEngineConfig, SharedKtor_client_coreHttpReceivePipeline, SharedKtor_client_coreHttpReceivePipelinePhases, SharedKtor_client_coreHttpRequestBuilder, SharedKtor_client_coreHttpRequestBuilderCompanion, SharedKtor_client_coreHttpRequestData, SharedKtor_client_coreHttpRequestPipeline, SharedKtor_client_coreHttpRequestPipelinePhases, SharedKtor_client_coreHttpResponse, SharedKtor_client_coreHttpResponseContainer, SharedKtor_client_coreHttpResponseData, SharedKtor_client_coreHttpResponsePipeline, SharedKtor_client_coreHttpResponsePipelinePhases, SharedKtor_client_coreHttpSendPipeline, SharedKtor_client_coreHttpSendPipelinePhases, SharedKtor_client_coreProxyConfig, SharedKtor_eventsEventDefinition<T>, SharedKtor_eventsEvents, SharedKtor_httpContentType, SharedKtor_httpContentTypeCompanion, SharedKtor_httpHeaderValueParam, SharedKtor_httpHeaderValueWithParameters, SharedKtor_httpHeaderValueWithParametersCompanion, SharedKtor_httpHeadersBuilder, SharedKtor_httpHttpMethod, SharedKtor_httpHttpMethodCompanion, SharedKtor_httpHttpProtocolVersion, SharedKtor_httpHttpProtocolVersionCompanion, SharedKtor_httpHttpStatusCode, SharedKtor_httpHttpStatusCodeCompanion, SharedKtor_httpOutgoingContent, SharedKtor_httpURLBuilder, SharedKtor_httpURLBuilderCompanion, SharedKtor_httpURLProtocol, SharedKtor_httpURLProtocolCompanion, SharedKtor_httpUrl, SharedKtor_httpUrlCompanion, SharedKtor_utilsAttributeKey<T>, SharedKtor_utilsGMTDate, SharedKtor_utilsGMTDateCompanion, SharedKtor_utilsMonth, SharedKtor_utilsMonthCompanion, SharedKtor_utilsPipeline<TSubject, TContext>, SharedKtor_utilsPipelinePhase, SharedKtor_utilsStringValuesBuilderImpl, SharedKtor_utilsTypeInfo, SharedKtor_utilsWeekDay, SharedKtor_utilsWeekDayCompanion, SharedLifecycle_viewmodelViewModel, SharedLocation, SharedLocationQueries, SharedLocations, SharedLogger, SharedMaterial3Typography, SharedRuntimeAfterVersion, SharedRuntimeBaseTransacterImpl, SharedRuntimeExecutableQuery<__covariant RowType>, SharedRuntimeQuery<__covariant RowType>, SharedRuntimeTransacterImpl, SharedRuntimeTransacterTransaction, SharedSkikoBackendRenderTarget, SharedSkikoBackendRenderTargetCompanion, SharedSkikoBitmap, SharedSkikoBitmapCompanion, SharedSkikoBlendMode, SharedSkikoCanvas, SharedSkikoCanvasCompanion, SharedSkikoCanvasSaveLayerFlags, SharedSkikoCanvasSaveLayerFlagsSet, SharedSkikoCanvasSaveLayerRec, SharedSkikoClipMode, SharedSkikoColor4f, SharedSkikoColor4fCompanion, SharedSkikoColorAlphaType, SharedSkikoColorChannel, SharedSkikoColorFilter, SharedSkikoColorFilterCompanion, SharedSkikoColorInfo, SharedSkikoColorInfoCompanion, SharedSkikoColorMatrix, SharedSkikoColorSpace, SharedSkikoColorSpaceCompanion, SharedSkikoColorType, SharedSkikoColorTypeCompanion, SharedSkikoContentChangeMode, SharedSkikoData, SharedSkikoDataCompanion, SharedSkikoDirectContext, SharedSkikoDirectContextCompanion, SharedSkikoDrawable, SharedSkikoDrawableCompanion, SharedSkikoEncodedImageFormat, SharedSkikoFilterBlurMode, SharedSkikoFilterMode, SharedSkikoFilterTileMode, SharedSkikoFont, SharedSkikoFontCompanion, SharedSkikoFontEdging, SharedSkikoFontFamilyName, SharedSkikoFontFeature, SharedSkikoFontFeatureCompanion, SharedSkikoFontHinting, SharedSkikoFontMetrics, SharedSkikoFontMetricsCompanion, SharedSkikoFontMgr, SharedSkikoFontMgrCompanion, SharedSkikoFontSlant, SharedSkikoFontStyle, SharedSkikoFontStyleCompanion, SharedSkikoFontStyleSet, SharedSkikoFontStyleSetCompanion, SharedSkikoFontVariation, SharedSkikoFontVariationAxis, SharedSkikoFontVariationCompanion, SharedSkikoGLBackendState, SharedSkikoGradientStyle, SharedSkikoGradientStyleCompanion, SharedSkikoIPoint, SharedSkikoIPointCompanion, SharedSkikoIRect, SharedSkikoIRectCompanion, SharedSkikoISize, SharedSkikoISizeCompanion, SharedSkikoImage, SharedSkikoImageCompanion, SharedSkikoImageFilter, SharedSkikoImageFilterCompanion, SharedSkikoImageInfo, SharedSkikoImageInfoCompanion, SharedSkikoInversionMode, SharedSkikoManaged, SharedSkikoMaskFilter, SharedSkikoMaskFilterCompanion, SharedSkikoMatcher, SharedSkikoMatrix22, SharedSkikoMatrix22Companion, SharedSkikoMatrix33, SharedSkikoMatrix33Companion, SharedSkikoMatrix44, SharedSkikoMatrix44Companion, SharedSkikoNative, SharedSkikoNativeCompanion, SharedSkikoPaint, SharedSkikoPaintCompanion, SharedSkikoPaintMode, SharedSkikoPaintStrokeCap, SharedSkikoPaintStrokeJoin, SharedSkikoPath, SharedSkikoPathCompanion, SharedSkikoPathDirection, SharedSkikoPathEffect, SharedSkikoPathEffectCompanion, SharedSkikoPathEffectStyle, SharedSkikoPathEllipseArc, SharedSkikoPathFillMode, SharedSkikoPathOp, SharedSkikoPathSegment, SharedSkikoPathSegmentIterator, SharedSkikoPathSegmentIteratorCompanion, SharedSkikoPathVerb, SharedSkikoPattern, SharedSkikoPicture, SharedSkikoPictureCompanion, SharedSkikoPixelGeometry, SharedSkikoPixelRef, SharedSkikoPixelRefCompanion, SharedSkikoPixmap, SharedSkikoPixmapCompanion, SharedSkikoPoint, SharedSkikoPointCompanion, SharedSkikoRRect, SharedSkikoRRectCompanion, SharedSkikoRSXform, SharedSkikoRSXformCompanion, SharedSkikoRect, SharedSkikoRectCompanion, SharedSkikoRefCnt, SharedSkikoRegion, SharedSkikoRegionCompanion, SharedSkikoRegionOp, SharedSkikoRegionOpCompanion, SharedSkikoRuntimeEffect, SharedSkikoRuntimeEffectCompanion, SharedSkikoRuntimeShaderBuilder, SharedSkikoRuntimeShaderBuilderCompanion, SharedSkikoShader, SharedSkikoShaderCompanion, SharedSkikoShapingOptions, SharedSkikoShapingOptionsCompanion, SharedSkikoSurface, SharedSkikoSurfaceColorFormat, SharedSkikoSurfaceCompanion, SharedSkikoSurfaceOrigin, SharedSkikoSurfaceProps, SharedSkikoTextBlob, SharedSkikoTextBlobCompanion, SharedSkikoTextLine, SharedSkikoTextLineCompanion, SharedSkikoTypeface, SharedSkikoTypefaceCompanion, SharedSkikoVertexMode, SharedTimeSlot, SharedTransitMode, SharedTransitOption, SharedTransitOptionQueries, SharedTransit_options, SharedTravlogueApiClientCompanion, SharedTravlogueDatabaseCompanion, SharedTravlogueDb, SharedTrip, SharedTripDto, SharedTripDtoCompanion, SharedTripMockData, SharedTripQueries, SharedTripRepository, SharedTrips, SharedUi_graphicsBrush, SharedUi_graphicsBrushCompanion, SharedUi_graphicsColorFilter, SharedUi_graphicsColorFilterCompanion, SharedUi_graphicsDrawStyle, SharedUi_graphicsShadow, SharedUi_graphicsShadowCompanion, SharedUi_textFontFamily, SharedUi_textFontFamilyCompanion, SharedUi_textFontHinting, SharedUi_textFontRasterizationSettings, SharedUi_textFontRasterizationSettingsCompanion, SharedUi_textFontSmoothing, SharedUi_textFontWeight, SharedUi_textFontWeightCompanion, SharedUi_textGenericFontFamily, SharedUi_textLineHeightStyle, SharedUi_textLineHeightStyleCompanion, SharedUi_textLocale, SharedUi_textLocaleCompanion, SharedUi_textLocaleList, SharedUi_textLocaleListCompanion, SharedUi_textParagraphStyle, SharedUi_textPlatformParagraphStyle, SharedUi_textPlatformParagraphStyleCompanion, SharedUi_textPlatformSpanStyle, SharedUi_textPlatformSpanStyleCompanion, SharedUi_textPlatformTextStyle, SharedUi_textSpanStyle, SharedUi_textSystemFontFamily, SharedUi_textTextDecoration, SharedUi_textTextDecorationCompanion, SharedUi_textTextGeometricTransform, SharedUi_textTextGeometricTransformCompanion, SharedUi_textTextIndent, SharedUi_textTextIndentCompanion, SharedUi_textTextMotion, SharedUi_textTextMotionCompanion, SharedUi_textTextStyle, SharedUi_textTextStyleCompanion;

@protocol SharedKoin_coreKoinComponent, SharedKoin_coreKoinExtension, SharedKoin_coreKoinScopeComponent, SharedKoin_coreQualifier, SharedKoin_coreScopeCallback, SharedKotlinAnnotation, SharedKotlinAppendable, SharedKotlinAutoCloseable, SharedKotlinClosedRange, SharedKotlinCollection, SharedKotlinComparable, SharedKotlinContinuation, SharedKotlinContinuationInterceptor, SharedKotlinCoroutineContext, SharedKotlinCoroutineContextElement, SharedKotlinCoroutineContextKey, SharedKotlinFunction, SharedKotlinIterable, SharedKotlinIterator, SharedKotlinKAnnotatedElement, SharedKotlinKClass, SharedKotlinKClassifier, SharedKotlinKDeclarationContainer, SharedKotlinKType, SharedKotlinLazy, SharedKotlinMapEntry, SharedKotlinMatchGroupCollection, SharedKotlinMatchResult, SharedKotlinMutableIterator, SharedKotlinOpenEndRange, SharedKotlinSequence, SharedKotlinSuspendFunction2, SharedKotlinx_coroutines_coreChildHandle, SharedKotlinx_coroutines_coreChildJob, SharedKotlinx_coroutines_coreCoroutineScope, SharedKotlinx_coroutines_coreDisposableHandle, SharedKotlinx_coroutines_coreFlow, SharedKotlinx_coroutines_coreFlowCollector, SharedKotlinx_coroutines_coreJob, SharedKotlinx_coroutines_coreParentJob, SharedKotlinx_coroutines_coreRunnable, SharedKotlinx_coroutines_coreSelectClause, SharedKotlinx_coroutines_coreSelectClause0, SharedKotlinx_coroutines_coreSelectInstance, SharedKotlinx_coroutines_coreSharedFlow, SharedKotlinx_coroutines_coreStateFlow, SharedKotlinx_datetimeDateTimeFormat, SharedKotlinx_datetimeDateTimeFormatBuilder, SharedKotlinx_datetimeDateTimeFormatBuilderWithDate, SharedKotlinx_datetimeDateTimeFormatBuilderWithDateTime, SharedKotlinx_datetimeDateTimeFormatBuilderWithTime, SharedKotlinx_io_coreRawSink, SharedKotlinx_io_coreRawSource, SharedKotlinx_io_coreSink, SharedKotlinx_io_coreSource, SharedKotlinx_serialization_coreCompositeDecoder, SharedKotlinx_serialization_coreCompositeEncoder, SharedKotlinx_serialization_coreDecoder, SharedKotlinx_serialization_coreDeserializationStrategy, SharedKotlinx_serialization_coreEncoder, SharedKotlinx_serialization_coreKSerializer, SharedKotlinx_serialization_coreSerialDescriptor, SharedKotlinx_serialization_coreSerializationStrategy, SharedKotlinx_serialization_coreSerializersModuleCollector, SharedKtor_client_coreHttpClientEngine, SharedKtor_client_coreHttpClientEngineCapability, SharedKtor_client_coreHttpClientPlugin, SharedKtor_client_coreHttpRequest, SharedKtor_httpHeaders, SharedKtor_httpHttpMessage, SharedKtor_httpHttpMessageBuilder, SharedKtor_httpParameters, SharedKtor_httpParametersBuilder, SharedKtor_ioByteReadChannel, SharedKtor_ioCloseable, SharedKtor_utilsAttributes, SharedKtor_utilsStringValues, SharedKtor_utilsStringValuesBuilder, SharedRuntimeCloseable, SharedRuntimeQueryListener, SharedRuntimeQueryResult, SharedRuntimeSqlCursor, SharedRuntimeSqlDriver, SharedRuntimeSqlPreparedStatement, SharedRuntimeSqlSchema, SharedRuntimeTransacter, SharedRuntimeTransacterBase, SharedRuntimeTransactionCallbacks, SharedRuntimeTransactionWithReturn, SharedRuntimeTransactionWithoutReturn, SharedSkikoIHasImageInfo, SharedSkikoSamplingMode, SharedTravlogueDatabase, SharedUi_graphicsPaint, SharedUi_graphicsPathEffect;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface SharedBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface SharedBase (SharedBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface SharedMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface SharedMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorSharedKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface SharedNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface SharedByte : SharedNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface SharedUByte : SharedNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface SharedShort : SharedNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface SharedUShort : SharedNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface SharedInt : SharedNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface SharedUInt : SharedNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface SharedLong : SharedNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface SharedULong : SharedNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface SharedFloat : SharedNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface SharedDouble : SharedNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface SharedBoolean : SharedNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((swift_name("Koin_coreKoinComponent")))
@protocol SharedKoin_coreKoinComponent
@required
- (SharedKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
@end


/**
 * Helper class to access Koin dependencies from Swift/iOS
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KoinHelper")))
@interface SharedKoinHelper : SharedBase <SharedKoin_coreKoinComponent>

/**
 * Helper class to access Koin dependencies from Swift/iOS
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Helper class to access Koin dependencies from Swift/iOS
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) SharedKoinHelperCompanion *companion __attribute__((swift_name("companion")));
- (SharedCreateTripViewModel *)getCreateTripViewModel __attribute__((swift_name("getCreateTripViewModel()")));
- (SharedHomeViewModel *)getHomeViewModel __attribute__((swift_name("getHomeViewModel()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KoinHelper.Companion")))
@interface SharedKoinHelperCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKoinHelperCompanion *shared __attribute__((swift_name("shared")));
- (void)doInitKoin __attribute__((swift_name("doInitKoin()")));
@property (readonly) SharedKoinHelper *shared __attribute__((swift_name("shared")));
@end


/**
 * Utility object for handling date and time conversions in Travlogue - KMP version.
 *
 * Date/Time Format Strategy:
 * - Trip dates: ISO date strings ("2025-11-15")
 * - Booking times: ISO 8601 with timezone ("2025-11-15T14:30:00+01:00")
 * - System timestamps: Long (milliseconds since epoch)
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DateTimeUtils")))
@interface SharedDateTimeUtils : SharedBase
+ (instancetype)alloc __attribute__((unavailable));

/**
 * Utility object for handling date and time conversions in Travlogue - KMP version.
 *
 * Date/Time Format Strategy:
 * - Trip dates: ISO date strings ("2025-11-15")
 * - Booking times: ISO 8601 with timezone ("2025-11-15T14:30:00+01:00")
 * - System timestamps: Long (milliseconds since epoch)
 */
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)dateTimeUtils __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedDateTimeUtils *shared __attribute__((swift_name("shared")));

/**
 * Create Instant from LocalDateTime and timezone string
 * Example: (2025-11-15T14:30:00, "Europe/Madrid") → Instant
 */
- (SharedKotlinx_datetimeInstant *)createInstantLocalDateTime:(SharedKotlinx_datetimeLocalDateTime *)localDateTime timezone:(NSString *)timezone __attribute__((swift_name("createInstant(localDateTime:timezone:)")));

/**
 * Calculate days between two ISO date strings
 * Example: ("2025-11-15", "2025-11-20") → 5
 */
- (int64_t)daysBetweenStartDate:(NSString *)startDate endDate:(NSString *)endDate __attribute__((swift_name("daysBetween(startDate:endDate:)")));

/**
 * Calculate duration between two booking times in minutes
 * Example: ("2025-11-15T14:30:00+01:00", "2025-11-15T16:45:00+01:00") → 135
 */
- (int64_t)durationInMinutesStartDateTime:(NSString *)startDateTime endDateTime:(NSString *)endDateTime __attribute__((swift_name("durationInMinutes(startDateTime:endDateTime:)")));

/**
 * Format booking time for display in system timezone
 * Example: "2025-11-15T14:30:00+01:00" → "Nov 15, 2025 at 2:30 PM"
 */
- (NSString *)formatBookingTimeForDisplay:(NSString *)receiver __attribute__((swift_name("formatBookingTimeForDisplay(_:)")));

/**
 * Parse ISO date string and format for display
 * Example: "2025-11-15" → "Nov 15, 2025"
 */
- (NSString *)formatDateForDisplay:(NSString *)receiver __attribute__((swift_name("formatDateForDisplay(_:)")));

/**
 * Format duration in minutes to human-readable string
 * Example: 135 → "2h 15m"
 */
- (NSString *)formatDurationMinutes:(int64_t)minutes __attribute__((swift_name("formatDuration(minutes:)")));

/**
 * Format time only for display
 * Example: "2025-11-15T14:30:00+01:00" → "2:30 PM"
 */
- (NSString *)formatTimeForDisplay:(NSString *)receiver __attribute__((swift_name("formatTimeForDisplay(_:)")));

/**
 * Get current time in specified timezone as ISO string
 * Example: ("Europe/Madrid") → "2025-11-15T14:30:00+01:00"
 */
- (NSString *)getCurrentTimeInTimezoneTimezone:(NSString *)timezone __attribute__((swift_name("getCurrentTimeInTimezone(timezone:)")));

/**
 * Get timezone display name (simplified for KMP)
 * Example: "Europe/Madrid" → "Europe/Madrid"
 */
- (NSString *)getTimezoneDisplayNameTimezone:(NSString *)timezone __attribute__((swift_name("getTimezoneDisplayName(timezone:)")));

/**
 * Check if one date is after another
 */
- (BOOL)isAfter:(SharedKotlinx_datetimeLocalDate *)receiver other:(SharedKotlinx_datetimeLocalDate *)other __attribute__((swift_name("isAfter(_:other:)")));

/**
 * Check if one date is before another
 */
- (BOOL)isBefore:(SharedKotlinx_datetimeLocalDate *)receiver other:(SharedKotlinx_datetimeLocalDate *)other __attribute__((swift_name("isBefore(_:other:)")));

/**
 * Check if date string is valid ISO format
 */
- (BOOL)isValidIsoDateDateString:(NSString *)dateString __attribute__((swift_name("isValidIsoDate(dateString:)")));

/**
 * Check if datetime string is valid ISO 8601 format
 */
- (BOOL)isValidIsoDateTimeDateTimeString:(NSString *)dateTimeString __attribute__((swift_name("isValidIsoDateTime(dateTimeString:)")));

/**
 * Check if timezone string is valid IANA timezone
 */
- (BOOL)isValidTimezoneTimezone:(NSString *)timezone __attribute__((swift_name("isValidTimezone(timezone:)")));

/**
 * Add days to a LocalDate
 */
- (SharedKotlinx_datetimeLocalDate *)plusDays:(SharedKotlinx_datetimeLocalDate *)receiver days:(int64_t)days __attribute__((swift_name("plusDays(_:days:)")));

/**
 * Convert timestamp to formatted display string
 * Example: 1699920000000L → "Nov 14, 2023"
 */
- (NSString *)toDisplayString:(int64_t)receiver __attribute__((swift_name("toDisplayString(_:)")));

/**
 * Format LocalDate for display
 * Example: 2025-11-15 → "Nov 15, 2025"
 */
- (NSString *)toDisplayString_:(SharedKotlinx_datetimeLocalDate *)receiver __attribute__((swift_name("toDisplayString(__:)")));

/**
 * Parse ISO 8601 datetime string to Instant
 * Example: "2025-11-15T14:30:00+01:00" → Instant
 */
- (SharedKotlinx_datetimeInstant *)toInstant:(NSString *)receiver __attribute__((swift_name("toInstant(_:)")));

/**
 * Convert Instant to ISO 8601 string
 * Example: Instant → "2025-11-15T14:30:00Z"
 */
- (NSString *)toIsoString:(SharedKotlinx_datetimeInstant *)receiver __attribute__((swift_name("toIsoString(_:)")));

/**
 * Convert LocalDate to ISO string format
 * Example: 2025-11-15 → "2025-11-15"
 */
- (NSString *)toIsoString_:(SharedKotlinx_datetimeLocalDate *)receiver __attribute__((swift_name("toIsoString(__:)")));

/**
 * Convert timestamp to LocalDate in system timezone
 * Example: 1699920000000L → LocalDate(2023, 11, 14)
 */
- (SharedKotlinx_datetimeLocalDate *)toLocalDate:(int64_t)receiver __attribute__((swift_name("toLocalDate(_:)")));

/**
 * Parse ISO date string to LocalDate
 * Example: "2025-11-15" → LocalDate(2025, 11, 15)
 */
- (SharedKotlinx_datetimeLocalDate *)toLocalDate_:(NSString *)receiver __attribute__((swift_name("toLocalDate(__:)")));

/**
 * Convert Instant to LocalDateTime in specified timezone
 */
- (SharedKotlinx_datetimeLocalDateTime *)toLocalDateTimeInTimezone:(SharedKotlinx_datetimeInstant *)receiver timezone:(NSString *)timezone __attribute__((swift_name("toLocalDateTimeInTimezone(_:timezone:)")));
@end


/**
 * iOS implementation of Logger using NSLog
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Logger")))
@interface SharedLogger : SharedBase
+ (instancetype)alloc __attribute__((unavailable));

/**
 * iOS implementation of Logger using NSLog
 */
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)logger __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedLogger *shared __attribute__((swift_name("shared")));
- (void)debugTag:(NSString *)tag message:(NSString *)message __attribute__((swift_name("debug(tag:message:)")));
- (void)errorTag:(NSString *)tag message:(NSString *)message throwable:(SharedKotlinThrowable * _Nullable)throwable __attribute__((swift_name("error(tag:message:throwable:)")));
- (void)infoTag:(NSString *)tag message:(NSString *)message __attribute__((swift_name("info(tag:message:)")));
- (void)warnTag:(NSString *)tag message:(NSString *)message __attribute__((swift_name("warn(tag:message:)")));
@end


/**
 * iOS implementation of DatabaseDriverFactory
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DatabaseDriverFactory")))
@interface SharedDatabaseDriverFactory : SharedBase

/**
 * iOS implementation of DatabaseDriverFactory
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * iOS implementation of DatabaseDriverFactory
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<SharedRuntimeSqlDriver>)createDriver __attribute__((swift_name("createDriver()")));
@end


/**
 * Wrapper class for TravlogueDatabase that provides easy access to all queries
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TravlogueDb")))
@interface SharedTravlogueDb : SharedBase
- (instancetype)initWithDriverFactory:(SharedDatabaseDriverFactory *)driverFactory __attribute__((swift_name("init(driverFactory:)"))) __attribute__((objc_designated_initializer));
@property (readonly) SharedActivityQueries *activityQueries __attribute__((swift_name("activityQueries")));
@property (readonly) SharedBookingQueries *bookingQueries __attribute__((swift_name("bookingQueries")));
@property (readonly) SharedGapQueries *gapQueries __attribute__((swift_name("gapQueries")));
@property (readonly) SharedLocationQueries *locationQueries __attribute__((swift_name("locationQueries")));
@property (readonly) SharedTransitOptionQueries *transitOptionQueries __attribute__((swift_name("transitOptionQueries")));
@property (readonly) SharedTripQueries *tripQueries __attribute__((swift_name("tripQueries")));
@end


/**
 * API client for Travlogue backend - KMP version using Ktor
 *
 * Note: This is a placeholder implementation. The actual API endpoints
 * will be implemented when the backend is available.
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TravlogueApiClient")))
@interface SharedTravlogueApiClient : SharedBase
- (instancetype)initWithHttpClient:(SharedKtor_client_coreHttpClient *)httpClient __attribute__((swift_name("init(httpClient:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedTravlogueApiClientCompanion *companion __attribute__((swift_name("companion")));

/**
 * Close the HTTP client
 */
- (void)close __attribute__((swift_name("close()")));

/**
 * Example: Get all trips for a user
 * This is a placeholder that would call the actual API
 *
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTripsWithCompletionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getTrips(completionHandler:)")));

/**
 * Example: Sync trip data to backend
 *
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)syncTripTrip:(SharedTripDto *)trip completionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("syncTrip(trip:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TravlogueApiClient.Companion")))
@interface SharedTravlogueApiClientCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedTravlogueApiClientCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *BASE_URL __attribute__((swift_name("BASE_URL")));
@property (readonly) int64_t TIMEOUT_MILLIS __attribute__((swift_name("TIMEOUT_MILLIS")));
@end


/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 *
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TripDto")))
@interface SharedTripDto : SharedBase
- (instancetype)initWithId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity __attribute__((swift_name("init(id:name:originCity:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedTripDtoCompanion *companion __attribute__((swift_name("companion")));
- (SharedTripDto *)doCopyId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity __attribute__((swift_name("doCopy(id:name:originCity:)")));

/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *originCity __attribute__((swift_name("originCity")));
@end


/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TripDto.Companion")))
@interface SharedTripDtoCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));

/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedTripDtoCompanion *shared __attribute__((swift_name("shared")));

/**
 * Placeholder DTO for Trip
 * In a real implementation, this would be in a separate dto package
 */
- (id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * Repository for Trip-related data operations - KMP version using SQLDelight
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TripRepository")))
@interface SharedTripRepository : SharedBase
- (instancetype)initWithDatabase:(SharedTravlogueDb *)database __attribute__((swift_name("init(database:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteActivityActivity:(SharedActivity *)activity completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteActivity(activity:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteActivityByIdActivityId:(NSString *)activityId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteActivityById(activityId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteAllGapsForTripTripId:(NSString *)tripId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteAllGapsForTrip(tripId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteBookingBooking:(SharedBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteBooking(booking:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteGapGap:(SharedGap *)gap completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteGap(gap:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteLocationLocation:(SharedLocation *)location completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteLocation(location:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteTripTrip:(SharedTrip *)trip completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteTrip(trip:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteTripByIdTripId:(NSString *)tripId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteTripById(tripId:completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getActivitiesForLocationLocationId:(NSString *)locationId __attribute__((swift_name("getActivitiesForLocation(locationId:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getActivitiesForTripTripId:(NSString *)tripId __attribute__((swift_name("getActivitiesForTrip(tripId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getActivitiesForTripSyncTripId:(NSString *)tripId completionHandler:(void (^)(NSArray<SharedActivity *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getActivitiesForTripSync(tripId:completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getBookingsForTripTripId:(NSString *)tripId __attribute__((swift_name("getBookingsForTrip(tripId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getBookingsForTripSyncTripId:(NSString *)tripId completionHandler:(void (^)(NSArray<SharedBooking *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getBookingsForTripSync(tripId:completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getGapsForTripTripId:(NSString *)tripId __attribute__((swift_name("getGapsForTrip(tripId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getGapsForTripSyncTripId:(NSString *)tripId completionHandler:(void (^)(NSArray<SharedGap *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getGapsForTripSync(tripId:completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getLocationsForTripTripId:(NSString *)tripId __attribute__((swift_name("getLocationsForTrip(tripId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getLocationsForTripSyncTripId:(NSString *)tripId completionHandler:(void (^)(NSArray<SharedLocation *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getLocationsForTripSync(tripId:completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getTripByIdTripId:(NSString *)tripId __attribute__((swift_name("getTripById(tripId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTripByIdSyncTripId:(NSString *)tripId completionHandler:(void (^)(SharedTrip * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getTripByIdSync(tripId:completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreFlow>)getUnresolvedGapsForTripTripId:(NSString *)tripId __attribute__((swift_name("getUnresolvedGapsForTrip(tripId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertActivityActivity:(SharedActivity *)activity completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insertActivity(activity:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertBookingBooking:(SharedBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insertBooking(booking:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertGapGap:(SharedGap *)gap completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insertGap(gap:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertGapsGaps:(NSArray<SharedGap *> *)gaps completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insertGaps(gaps:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertLocationLocation:(SharedLocation *)location completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insertLocation(location:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertTripTrip:(SharedTrip *)trip completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insertTrip(trip:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)markGapAsResolvedGapId:(NSString *)gapId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("markGapAsResolved(gapId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateActivityActivity:(SharedActivity *)activity completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("updateActivity(activity:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateBookingBooking:(SharedBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("updateBooking(booking:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateGapGap:(SharedGap *)gap completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("updateGap(gap:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateLocationLocation:(SharedLocation *)location completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("updateLocation(location:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateTripTrip:(SharedTrip *)trip completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("updateTrip(trip:completionHandler:)")));
@property (readonly) id<SharedKotlinx_coroutines_coreFlow> allTrips __attribute__((swift_name("allTrips")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ColorFamily")))
@interface SharedColorFamily : SharedBase
- (instancetype)initWithColor:(uint64_t)color onColor:(uint64_t)onColor colorContainer:(uint64_t)colorContainer onColorContainer:(uint64_t)onColorContainer __attribute__((swift_name("init(color:onColor:colorContainer:onColorContainer:)"))) __attribute__((objc_designated_initializer));
- (SharedColorFamily *)doCopyColor:(uint64_t)color onColor:(uint64_t)onColor colorContainer:(uint64_t)colorContainer onColorContainer:(uint64_t)onColorContainer __attribute__((swift_name("doCopy(color:onColor:colorContainer:onColorContainer:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) uint64_t colorContainer __attribute__((swift_name("colorContainer")));
@property (readonly) uint64_t onColor __attribute__((swift_name("onColor")));
@property (readonly) uint64_t onColorContainer __attribute__((swift_name("onColorContainer")));
@end


/**
 * Activity domain model - KMP compatible version
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Activity")))
@interface SharedActivity : SharedBase
- (instancetype)initWithId:(NSString *)id locationId:(NSString *)locationId title:(NSString *)title description:(NSString * _Nullable)description date:(NSString * _Nullable)date timeSlot:(SharedTimeSlot * _Nullable)timeSlot type:(SharedActivityType *)type startTime:(NSString * _Nullable)startTime endTime:(NSString * _Nullable)endTime __attribute__((swift_name("init(id:locationId:title:description:date:timeSlot:type:startTime:endTime:)"))) __attribute__((objc_designated_initializer));
- (SharedActivity *)doCopyId:(NSString *)id locationId:(NSString *)locationId title:(NSString *)title description:(NSString * _Nullable)description date:(NSString * _Nullable)date timeSlot:(SharedTimeSlot * _Nullable)timeSlot type:(SharedActivityType *)type startTime:(NSString * _Nullable)startTime endTime:(NSString * _Nullable)endTime __attribute__((swift_name("doCopy(id:locationId:title:description:date:timeSlot:type:startTime:endTime:)")));

/**
 * Activity domain model - KMP compatible version
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * Activity domain model - KMP compatible version
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * Activity domain model - KMP compatible version
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable date __attribute__((swift_name("date")));
@property (readonly) NSString * _Nullable description_ __attribute__((swift_name("description_")));
@property (readonly) NSString * _Nullable endTime __attribute__((swift_name("endTime")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *locationId __attribute__((swift_name("locationId")));
@property (readonly) NSString * _Nullable startTime __attribute__((swift_name("startTime")));
@property (readonly) SharedTimeSlot * _Nullable timeSlot __attribute__((swift_name("timeSlot")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@property (readonly) SharedActivityType *type __attribute__((swift_name("type")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol SharedKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface SharedKotlinEnum<E> : SharedBase <SharedKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActivityType")))
@interface SharedActivityType : SharedKotlinEnum<SharedActivityType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedActivityType *attraction __attribute__((swift_name("attraction")));
@property (class, readonly) SharedActivityType *food __attribute__((swift_name("food")));
@property (class, readonly) SharedActivityType *booking __attribute__((swift_name("booking")));
@property (class, readonly) SharedActivityType *transit __attribute__((swift_name("transit")));
@property (class, readonly) SharedActivityType *other __attribute__((swift_name("other")));
+ (SharedKotlinArray<SharedActivityType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedActivityType *> *entries __attribute__((swift_name("entries")));
@end


/**
 * Booking domain model - KMP compatible version
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Booking")))
@interface SharedBooking : SharedBase
- (instancetype)initWithId:(NSString *)id tripId:(NSString *)tripId type:(SharedBookingType *)type confirmationNumber:(NSString * _Nullable)confirmationNumber provider:(NSString *)provider startDateTime:(NSString *)startDateTime endDateTime:(NSString * _Nullable)endDateTime timezone:(NSString *)timezone endTimezone:(NSString * _Nullable)endTimezone fromLocation:(NSString * _Nullable)fromLocation toLocation:(NSString * _Nullable)toLocation price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency notes:(NSString * _Nullable)notes imageUri:(NSString * _Nullable)imageUri __attribute__((swift_name("init(id:tripId:type:confirmationNumber:provider:startDateTime:endDateTime:timezone:endTimezone:fromLocation:toLocation:price:currency:notes:imageUri:)"))) __attribute__((objc_designated_initializer));
- (SharedBooking *)doCopyId:(NSString *)id tripId:(NSString *)tripId type:(SharedBookingType *)type confirmationNumber:(NSString * _Nullable)confirmationNumber provider:(NSString *)provider startDateTime:(NSString *)startDateTime endDateTime:(NSString * _Nullable)endDateTime timezone:(NSString *)timezone endTimezone:(NSString * _Nullable)endTimezone fromLocation:(NSString * _Nullable)fromLocation toLocation:(NSString * _Nullable)toLocation price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency notes:(NSString * _Nullable)notes imageUri:(NSString * _Nullable)imageUri __attribute__((swift_name("doCopy(id:tripId:type:confirmationNumber:provider:startDateTime:endDateTime:timezone:endTimezone:fromLocation:toLocation:price:currency:notes:imageUri:)")));

/**
 * Booking domain model - KMP compatible version
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * Booking domain model - KMP compatible version
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * Booking domain model - KMP compatible version
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable confirmationNumber __attribute__((swift_name("confirmationNumber")));
@property (readonly) NSString * _Nullable currency __attribute__((swift_name("currency")));
@property (readonly) NSString * _Nullable endDateTime __attribute__((swift_name("endDateTime")));
@property (readonly) NSString * _Nullable endTimezone __attribute__((swift_name("endTimezone")));
@property (readonly) NSString * _Nullable fromLocation __attribute__((swift_name("fromLocation")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable imageUri __attribute__((swift_name("imageUri")));
@property (readonly) NSString * _Nullable notes __attribute__((swift_name("notes")));
@property (readonly) SharedDouble * _Nullable price __attribute__((swift_name("price")));
@property (readonly) NSString *provider __attribute__((swift_name("provider")));
@property (readonly) NSString *startDateTime __attribute__((swift_name("startDateTime")));
@property (readonly) NSString *timezone __attribute__((swift_name("timezone")));
@property (readonly) NSString * _Nullable toLocation __attribute__((swift_name("toLocation")));
@property (readonly) NSString *tripId __attribute__((swift_name("tripId")));
@property (readonly) SharedBookingType *type __attribute__((swift_name("type")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BookingType")))
@interface SharedBookingType : SharedKotlinEnum<SharedBookingType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedBookingType *flight __attribute__((swift_name("flight")));
@property (class, readonly) SharedBookingType *hotel __attribute__((swift_name("hotel")));
@property (class, readonly) SharedBookingType *train __attribute__((swift_name("train")));
@property (class, readonly) SharedBookingType *bus __attribute__((swift_name("bus")));
@property (class, readonly) SharedBookingType *ticket __attribute__((swift_name("ticket")));
@property (class, readonly) SharedBookingType *other __attribute__((swift_name("other")));
+ (SharedKotlinArray<SharedBookingType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedBookingType *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DateType")))
@interface SharedDateType : SharedKotlinEnum<SharedDateType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedDateType *fixed __attribute__((swift_name("fixed")));
@property (class, readonly) SharedDateType *flexible __attribute__((swift_name("flexible")));
+ (SharedKotlinArray<SharedDateType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedDateType *> *entries __attribute__((swift_name("entries")));
@end


/**
 * Gap domain model - KMP compatible version
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Gap")))
@interface SharedGap : SharedBase
- (instancetype)initWithId:(NSString *)id tripId:(NSString *)tripId gapType:(SharedGapType *)gapType fromLocationId:(NSString *)fromLocationId toLocationId:(NSString *)toLocationId fromDate:(NSString *)fromDate toDate:(NSString *)toDate isResolved:(BOOL)isResolved __attribute__((swift_name("init(id:tripId:gapType:fromLocationId:toLocationId:fromDate:toDate:isResolved:)"))) __attribute__((objc_designated_initializer));
- (SharedGap *)doCopyId:(NSString *)id tripId:(NSString *)tripId gapType:(SharedGapType *)gapType fromLocationId:(NSString *)fromLocationId toLocationId:(NSString *)toLocationId fromDate:(NSString *)fromDate toDate:(NSString *)toDate isResolved:(BOOL)isResolved __attribute__((swift_name("doCopy(id:tripId:gapType:fromLocationId:toLocationId:fromDate:toDate:isResolved:)")));

/**
 * Gap domain model - KMP compatible version
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * Gap domain model - KMP compatible version
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * Gap domain model - KMP compatible version
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *fromDate __attribute__((swift_name("fromDate")));
@property (readonly) NSString *fromLocationId __attribute__((swift_name("fromLocationId")));
@property (readonly) SharedGapType *gapType __attribute__((swift_name("gapType")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isResolved __attribute__((swift_name("isResolved")));
@property (readonly) NSString *toDate __attribute__((swift_name("toDate")));
@property (readonly) NSString *toLocationId __attribute__((swift_name("toLocationId")));
@property (readonly) NSString *tripId __attribute__((swift_name("tripId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GapType")))
@interface SharedGapType : SharedKotlinEnum<SharedGapType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedGapType *missingTransit __attribute__((swift_name("missingTransit")));
@property (class, readonly) SharedGapType *unplannedDay __attribute__((swift_name("unplannedDay")));
@property (class, readonly) SharedGapType *timeConflict __attribute__((swift_name("timeConflict")));
+ (SharedKotlinArray<SharedGapType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedGapType *> *entries __attribute__((swift_name("entries")));
@end


/**
 * Location domain model - KMP compatible version
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Location")))
@interface SharedLocation : SharedBase
- (instancetype)initWithId:(NSString *)id tripId:(NSString *)tripId name:(NSString *)name country:(NSString *)country date:(NSString * _Nullable)date latitude:(SharedDouble * _Nullable)latitude longitude:(SharedDouble * _Nullable)longitude order:(int32_t)order timezone:(NSString * _Nullable)timezone arrivalDateTime:(NSString * _Nullable)arrivalDateTime departureDateTime:(NSString * _Nullable)departureDateTime __attribute__((swift_name("init(id:tripId:name:country:date:latitude:longitude:order:timezone:arrivalDateTime:departureDateTime:)"))) __attribute__((objc_designated_initializer));
- (SharedLocation *)doCopyId:(NSString *)id tripId:(NSString *)tripId name:(NSString *)name country:(NSString *)country date:(NSString * _Nullable)date latitude:(SharedDouble * _Nullable)latitude longitude:(SharedDouble * _Nullable)longitude order:(int32_t)order timezone:(NSString * _Nullable)timezone arrivalDateTime:(NSString * _Nullable)arrivalDateTime departureDateTime:(NSString * _Nullable)departureDateTime __attribute__((swift_name("doCopy(id:tripId:name:country:date:latitude:longitude:order:timezone:arrivalDateTime:departureDateTime:)")));

/**
 * Location domain model - KMP compatible version
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * Location domain model - KMP compatible version
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * Location domain model - KMP compatible version
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable arrivalDateTime __attribute__((swift_name("arrivalDateTime")));
@property (readonly) NSString *country __attribute__((swift_name("country")));
@property (readonly) NSString * _Nullable date __attribute__((swift_name("date")));
@property (readonly) NSString * _Nullable departureDateTime __attribute__((swift_name("departureDateTime")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) SharedDouble * _Nullable latitude __attribute__((swift_name("latitude")));
@property (readonly) SharedDouble * _Nullable longitude __attribute__((swift_name("longitude")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t order __attribute__((swift_name("order")));
@property (readonly) NSString * _Nullable timezone __attribute__((swift_name("timezone")));
@property (readonly) NSString *tripId __attribute__((swift_name("tripId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TimeSlot")))
@interface SharedTimeSlot : SharedKotlinEnum<SharedTimeSlot *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedTimeSlot *morning __attribute__((swift_name("morning")));
@property (class, readonly) SharedTimeSlot *afternoon __attribute__((swift_name("afternoon")));
@property (class, readonly) SharedTimeSlot *evening __attribute__((swift_name("evening")));
@property (class, readonly) SharedTimeSlot *fullDay __attribute__((swift_name("fullDay")));
+ (SharedKotlinArray<SharedTimeSlot *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedTimeSlot *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TransitMode")))
@interface SharedTransitMode : SharedKotlinEnum<SharedTransitMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedTransitMode *flight __attribute__((swift_name("flight")));
@property (class, readonly) SharedTransitMode *train __attribute__((swift_name("train")));
@property (class, readonly) SharedTransitMode *bus __attribute__((swift_name("bus")));
@property (class, readonly) SharedTransitMode *car __attribute__((swift_name("car")));
@property (class, readonly) SharedTransitMode *ferry __attribute__((swift_name("ferry")));
+ (SharedKotlinArray<SharedTransitMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedTransitMode *> *entries __attribute__((swift_name("entries")));
@end


/**
 * TransitOption domain model - KMP compatible version
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TransitOption")))
@interface SharedTransitOption : SharedBase
- (instancetype)initWithId:(NSString *)id gapId:(NSString *)gapId mode:(SharedTransitMode *)mode provider:(NSString * _Nullable)provider duration:(int32_t)duration price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency departureTime:(NSString * _Nullable)departureTime arrivalTime:(NSString * _Nullable)arrivalTime fetchedAt:(int64_t)fetchedAt __attribute__((swift_name("init(id:gapId:mode:provider:duration:price:currency:departureTime:arrivalTime:fetchedAt:)"))) __attribute__((objc_designated_initializer));
- (SharedTransitOption *)doCopyId:(NSString *)id gapId:(NSString *)gapId mode:(SharedTransitMode *)mode provider:(NSString * _Nullable)provider duration:(int32_t)duration price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency departureTime:(NSString * _Nullable)departureTime arrivalTime:(NSString * _Nullable)arrivalTime fetchedAt:(int64_t)fetchedAt __attribute__((swift_name("doCopy(id:gapId:mode:provider:duration:price:currency:departureTime:arrivalTime:fetchedAt:)")));

/**
 * TransitOption domain model - KMP compatible version
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * TransitOption domain model - KMP compatible version
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * TransitOption domain model - KMP compatible version
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable arrivalTime __attribute__((swift_name("arrivalTime")));
@property (readonly) NSString * _Nullable currency __attribute__((swift_name("currency")));
@property (readonly) NSString * _Nullable departureTime __attribute__((swift_name("departureTime")));
@property (readonly) int32_t duration __attribute__((swift_name("duration")));
@property (readonly) int64_t fetchedAt __attribute__((swift_name("fetchedAt")));
@property (readonly) NSString *gapId __attribute__((swift_name("gapId")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) SharedTransitMode *mode __attribute__((swift_name("mode")));
@property (readonly) SharedDouble * _Nullable price __attribute__((swift_name("price")));
@property (readonly) NSString * _Nullable provider __attribute__((swift_name("provider")));
@end


/**
 * Trip domain model - KMP compatible version
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Trip")))
@interface SharedTrip : SharedBase
- (instancetype)initWithId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity dateType:(SharedDateType *)dateType startDate:(NSString * _Nullable)startDate endDate:(NSString * _Nullable)endDate flexibleMonth:(NSString * _Nullable)flexibleMonth flexibleDuration:(SharedInt * _Nullable)flexibleDuration createdAt:(int64_t)createdAt updatedAt:(int64_t)updatedAt __attribute__((swift_name("init(id:name:originCity:dateType:startDate:endDate:flexibleMonth:flexibleDuration:createdAt:updatedAt:)"))) __attribute__((objc_designated_initializer));
- (SharedTrip *)doCopyId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity dateType:(SharedDateType *)dateType startDate:(NSString * _Nullable)startDate endDate:(NSString * _Nullable)endDate flexibleMonth:(NSString * _Nullable)flexibleMonth flexibleDuration:(SharedInt * _Nullable)flexibleDuration createdAt:(int64_t)createdAt updatedAt:(int64_t)updatedAt __attribute__((swift_name("doCopy(id:name:originCity:dateType:startDate:endDate:flexibleMonth:flexibleDuration:createdAt:updatedAt:)")));

/**
 * Trip domain model - KMP compatible version
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * Trip domain model - KMP compatible version
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * Trip domain model - KMP compatible version
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t createdAt __attribute__((swift_name("createdAt")));
@property (readonly) SharedDateType *dateType __attribute__((swift_name("dateType")));
@property (readonly) NSString * _Nullable endDate __attribute__((swift_name("endDate")));
@property (readonly) SharedInt * _Nullable flexibleDuration __attribute__((swift_name("flexibleDuration")));
@property (readonly) NSString * _Nullable flexibleMonth __attribute__((swift_name("flexibleMonth")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *originCity __attribute__((swift_name("originCity")));
@property (readonly) NSString * _Nullable startDate __attribute__((swift_name("startDate")));
@property (readonly) int64_t updatedAt __attribute__((swift_name("updatedAt")));
@end


/**
 * Mock trip data for previews and testing
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TripMockData")))
@interface SharedTripMockData : SharedBase
+ (instancetype)alloc __attribute__((unavailable));

/**
 * Mock trip data for previews and testing
 */
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)tripMockData __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedTripMockData *shared __attribute__((swift_name("shared")));

/**
 * All available mock trips
 */
@property (readonly) NSArray<SharedTrip *> *allMockTrips __attribute__((swift_name("allMockTrips")));
@property (readonly) SharedTrip *europeBackpacking __attribute__((swift_name("europeBackpacking")));
@property (readonly) SharedTrip *icelandRoadTrip __attribute__((swift_name("icelandRoadTrip")));
@property (readonly) SharedTrip *japanCherryBlossom __attribute__((swift_name("japanCherryBlossom")));

/**
 * Sample list of trips for previews
 */
@property (readonly) NSArray<SharedTrip *> *sampleTrips __attribute__((swift_name("sampleTrips")));
@property (readonly) SharedTrip *spainAdventure __attribute__((swift_name("spainAdventure")));
@property (readonly) SharedTrip *weekendGetaway __attribute__((swift_name("weekendGetaway")));
@end


/**
 * Service responsible for synchronizing booking data with location arrival/departure times - KMP version.
 *
 * When transit bookings (flights, trains, buses) are added or updated, this service
 * automatically updates the corresponding location's arrivalDateTime and departureDateTime.
 *
 * Note: This is the domain service. Repository interaction will be handled by use cases.
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BookingSyncService")))
@interface SharedBookingSyncService : SharedBase

/**
 * Service responsible for synchronizing booking data with location arrival/departure times - KMP version.
 *
 * When transit bookings (flights, trains, buses) are added or updated, this service
 * automatically updates the corresponding location's arrivalDateTime and departureDateTime.
 *
 * Note: This is the domain service. Repository interaction will be handled by use cases.
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Service responsible for synchronizing booking data with location arrival/departure times - KMP version.
 *
 * When transit bookings (flights, trains, buses) are added or updated, this service
 * automatically updates the corresponding location's arrivalDateTime and departureDateTime.
 *
 * Note: This is the domain service. Repository interaction will be handled by use cases.
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 * Determines which locations need to be updated based on bookings.
 * Returns a list of updated locations.
 *
 * @param locations All locations for the trip
 * @param bookings All bookings for the trip
 * @return List of locations that have been updated
 */
- (NSArray<SharedLocation *> *)syncBookingsWithLocationsLocations:(NSArray<SharedLocation *> *)locations bookings:(NSArray<SharedBooking *> *)bookings __attribute__((swift_name("syncBookingsWithLocations(locations:bookings:)")));

/**
 * Updates a single location's arrival and departure times based on transit bookings.
 *
 * Logic:
 * - Arrival time: Set from the booking that arrives AT this location (toLocation matches)
 * - Departure time: Set from the booking that departs FROM this location (fromLocation matches)
 *
 * @param location The location to update
 * @param transitBookings All transit bookings for the trip
 * @return Updated location with new arrival/departure times, or original if no changes
 */
- (SharedLocation *)updateLocationWithBookingsLocation:(SharedLocation *)location transitBookings:(NSArray<SharedBooking *> *)transitBookings __attribute__((swift_name("updateLocationWithBookings(location:transitBookings:)")));
@end


/**
 * Service for detecting gaps in trip itineraries - KMP version.
 *
 * Detects:
 * 1. MISSING_TRANSIT - Location changes without transit bookings
 * 2. UNPLANNED_DAY - Days with no planned activities or locations
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GapDetectionService")))
@interface SharedGapDetectionService : SharedBase

/**
 * Service for detecting gaps in trip itineraries - KMP version.
 *
 * Detects:
 * 1. MISSING_TRANSIT - Location changes without transit bookings
 * 2. UNPLANNED_DAY - Days with no planned activities or locations
 */
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));

/**
 * Service for detecting gaps in trip itineraries - KMP version.
 *
 * Detects:
 * 1. MISSING_TRANSIT - Location changes without transit bookings
 * 2. UNPLANNED_DAY - Days with no planned activities or locations
 */
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 * Detect all gaps for a given trip.
 *
 * @param trip The trip to analyze
 * @param locations List of locations ordered by trip sequence
 * @param bookings List of bookings for the trip
 * @return List of detected gaps
 */
- (NSArray<SharedGap *> *)detectGapsTrip:(SharedTrip *)trip locations:(NSArray<SharedLocation *> *)locations bookings:(NSArray<SharedBooking *> *)bookings __attribute__((swift_name("detectGaps(trip:locations:bookings:)")));

/**
 * Get a human-readable description of the gap.
 */
- (NSString *)getGapDescriptionGap:(SharedGap *)gap fromLocation:(SharedLocation * _Nullable)fromLocation toLocation:(SharedLocation * _Nullable)toLocation __attribute__((swift_name("getGapDescription(gap:fromLocation:toLocation:)")));

/**
 * Calculate the duration of a gap in days.
 */
- (int64_t)getGapDurationDaysGap:(SharedGap *)gap __attribute__((swift_name("getGapDurationDays(gap:)")));
@end


/**
 * Use case to create a new trip
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateTripUseCase")))
@interface SharedCreateTripUseCase : SharedBase
- (instancetype)initWithRepository:(SharedTripRepository *)repository __attribute__((swift_name("init(repository:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeTrip:(SharedTrip *)trip completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(trip:completionHandler:)")));
@end


/**
 * Use case to delete a trip
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteTripUseCase")))
@interface SharedDeleteTripUseCase : SharedBase
- (instancetype)initWithRepository:(SharedTripRepository *)repository __attribute__((swift_name("init(repository:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeTripId:(NSString *)tripId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(tripId:completionHandler:)")));
@end


/**
 * Use case to detect gaps in a trip's itinerary
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DetectTripGapsUseCase")))
@interface SharedDetectTripGapsUseCase : SharedBase
- (instancetype)initWithRepository:(SharedTripRepository *)repository gapDetectionService:(SharedGapDetectionService *)gapDetectionService __attribute__((swift_name("init(repository:gapDetectionService:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeTripId:(NSString *)tripId completionHandler:(void (^)(NSArray<SharedGap *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(tripId:completionHandler:)")));
@end


/**
 * Use case to get all trips
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetAllTripsUseCase")))
@interface SharedGetAllTripsUseCase : SharedBase
- (instancetype)initWithRepository:(SharedTripRepository *)repository __attribute__((swift_name("init(repository:)"))) __attribute__((objc_designated_initializer));
- (id<SharedKotlinx_coroutines_coreFlow>)invoke __attribute__((swift_name("invoke()")));
@end


/**
 * Use case to get a trip by ID
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetTripByIdUseCase")))
@interface SharedGetTripByIdUseCase : SharedBase
- (instancetype)initWithRepository:(SharedTripRepository *)repository __attribute__((swift_name("init(repository:)"))) __attribute__((objc_designated_initializer));
- (id<SharedKotlinx_coroutines_coreFlow>)invokeTripId:(NSString *)tripId __attribute__((swift_name("invoke(tripId:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Activities")))
@interface SharedActivities : SharedBase
- (instancetype)initWithId:(NSString *)id locationId:(NSString *)locationId title:(NSString *)title description:(NSString * _Nullable)description date:(NSString * _Nullable)date timeSlot:(NSString * _Nullable)timeSlot type:(NSString *)type startTime:(NSString * _Nullable)startTime endTime:(NSString * _Nullable)endTime __attribute__((swift_name("init(id:locationId:title:description:date:timeSlot:type:startTime:endTime:)"))) __attribute__((objc_designated_initializer));
- (SharedActivities *)doCopyId:(NSString *)id locationId:(NSString *)locationId title:(NSString *)title description:(NSString * _Nullable)description date:(NSString * _Nullable)date timeSlot:(NSString * _Nullable)timeSlot type:(NSString *)type startTime:(NSString * _Nullable)startTime endTime:(NSString * _Nullable)endTime __attribute__((swift_name("doCopy(id:locationId:title:description:date:timeSlot:type:startTime:endTime:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable date __attribute__((swift_name("date")));
@property (readonly) NSString * _Nullable description_ __attribute__((swift_name("description_")));
@property (readonly) NSString * _Nullable endTime __attribute__((swift_name("endTime")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *locationId __attribute__((swift_name("locationId")));
@property (readonly) NSString * _Nullable startTime __attribute__((swift_name("startTime")));
@property (readonly) NSString * _Nullable timeSlot __attribute__((swift_name("timeSlot")));
@property (readonly) NSString *title __attribute__((swift_name("title")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end

__attribute__((swift_name("RuntimeBaseTransacterImpl")))
@interface SharedRuntimeBaseTransacterImpl : SharedBase
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSString *)createArgumentsCount:(int32_t)count __attribute__((swift_name("createArguments(count:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)notifyQueriesIdentifier:(int32_t)identifier tableProvider:(void (^)(SharedKotlinUnit *(^)(NSString *)))tableProvider __attribute__((swift_name("notifyQueries(identifier:tableProvider:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (id _Nullable)postTransactionCleanupTransaction:(SharedRuntimeTransacterTransaction *)transaction enclosing:(SharedRuntimeTransacterTransaction * _Nullable)enclosing thrownException:(SharedKotlinThrowable * _Nullable)thrownException returnValue:(id _Nullable)returnValue __attribute__((swift_name("postTransactionCleanup(transaction:enclosing:thrownException:returnValue:)")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) id<SharedRuntimeSqlDriver> driver __attribute__((swift_name("driver")));
@end

__attribute__((swift_name("RuntimeTransacterBase")))
@protocol SharedRuntimeTransacterBase
@required
@end

__attribute__((swift_name("RuntimeTransacter")))
@protocol SharedRuntimeTransacter <SharedRuntimeTransacterBase>
@required
- (void)transactionNoEnclosing:(BOOL)noEnclosing body:(void (^)(id<SharedRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(noEnclosing:body:)")));
- (id _Nullable)transactionWithResultNoEnclosing:(BOOL)noEnclosing bodyWithReturn:(id _Nullable (^)(id<SharedRuntimeTransactionWithReturn>))bodyWithReturn __attribute__((swift_name("transactionWithResult(noEnclosing:bodyWithReturn:)")));
@end

__attribute__((swift_name("RuntimeTransacterImpl")))
@interface SharedRuntimeTransacterImpl : SharedRuntimeBaseTransacterImpl <SharedRuntimeTransacter>
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)transactionNoEnclosing:(BOOL)noEnclosing body:(void (^)(id<SharedRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(noEnclosing:body:)")));
- (id _Nullable)transactionWithResultNoEnclosing:(BOOL)noEnclosing bodyWithReturn:(id _Nullable (^)(id<SharedRuntimeTransactionWithReturn>))bodyWithReturn __attribute__((swift_name("transactionWithResult(noEnclosing:bodyWithReturn:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActivityQueries")))
@interface SharedActivityQueries : SharedRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)deleteActivitiesByLocationIdLocationId:(NSString *)locationId __attribute__((swift_name("deleteActivitiesByLocationId(locationId:)")));
- (void)deleteActivityId:(NSString *)id __attribute__((swift_name("deleteActivity(id:)")));
- (SharedRuntimeQuery<SharedActivities *> *)getActivitiesByLocationIdLocationId:(NSString *)locationId __attribute__((swift_name("getActivitiesByLocationId(locationId:)")));
- (SharedRuntimeQuery<id> *)getActivitiesByLocationIdLocationId:(NSString *)locationId mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString *, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getActivitiesByLocationId(locationId:mapper:)")));
- (SharedRuntimeQuery<SharedActivities *> *)getActivitiesByTripIdTripId:(NSString *)tripId __attribute__((swift_name("getActivitiesByTripId(tripId:)")));
- (SharedRuntimeQuery<id> *)getActivitiesByTripIdTripId:(NSString *)tripId mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString *, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getActivitiesByTripId(tripId:mapper:)")));
- (SharedRuntimeQuery<SharedActivities *> *)getActivityByIdId:(NSString *)id __attribute__((swift_name("getActivityById(id:)")));
- (SharedRuntimeQuery<id> *)getActivityByIdId:(NSString *)id mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString *, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getActivityById(id:mapper:)")));
- (void)insertActivityId:(NSString *)id locationId:(NSString *)locationId title:(NSString *)title description:(NSString * _Nullable)description date:(NSString * _Nullable)date timeSlot:(NSString * _Nullable)timeSlot type:(NSString *)type startTime:(NSString * _Nullable)startTime endTime:(NSString * _Nullable)endTime __attribute__((swift_name("insertActivity(id:locationId:title:description:date:timeSlot:type:startTime:endTime:)")));
- (void)updateActivityTitle:(NSString *)title description:(NSString * _Nullable)description date:(NSString * _Nullable)date timeSlot:(NSString * _Nullable)timeSlot type:(NSString *)type startTime:(NSString * _Nullable)startTime endTime:(NSString * _Nullable)endTime id:(NSString *)id __attribute__((swift_name("updateActivity(title:description:date:timeSlot:type:startTime:endTime:id:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BookingQueries")))
@interface SharedBookingQueries : SharedRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)deleteBookingId:(NSString *)id __attribute__((swift_name("deleteBooking(id:)")));
- (void)deleteBookingsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("deleteBookingsByTripId(tripId:)")));
- (SharedRuntimeQuery<SharedBookings *> *)getBookingByIdId:(NSString *)id __attribute__((swift_name("getBookingById(id:)")));
- (SharedRuntimeQuery<id> *)getBookingByIdId:(NSString *)id mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, NSString *, NSString *, NSString * _Nullable, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedDouble * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getBookingById(id:mapper:)")));
- (SharedRuntimeQuery<SharedBookings *> *)getBookingsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("getBookingsByTripId(tripId:)")));
- (SharedRuntimeQuery<id> *)getBookingsByTripIdTripId:(NSString *)tripId mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, NSString *, NSString *, NSString * _Nullable, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedDouble * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getBookingsByTripId(tripId:mapper:)")));
- (SharedRuntimeQuery<SharedBookings *> *)getTransitBookingsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("getTransitBookingsByTripId(tripId:)")));
- (SharedRuntimeQuery<id> *)getTransitBookingsByTripIdTripId:(NSString *)tripId mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, NSString *, NSString *, NSString * _Nullable, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedDouble * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getTransitBookingsByTripId(tripId:mapper:)")));
- (void)insertBookingId:(NSString *)id tripId:(NSString *)tripId type:(NSString *)type confirmationNumber:(NSString * _Nullable)confirmationNumber provider:(NSString *)provider startDateTime:(NSString *)startDateTime endDateTime:(NSString * _Nullable)endDateTime timezone:(NSString *)timezone endTimezone:(NSString * _Nullable)endTimezone fromLocation:(NSString * _Nullable)fromLocation toLocation:(NSString * _Nullable)toLocation price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency notes:(NSString * _Nullable)notes imageUri:(NSString * _Nullable)imageUri __attribute__((swift_name("insertBooking(id:tripId:type:confirmationNumber:provider:startDateTime:endDateTime:timezone:endTimezone:fromLocation:toLocation:price:currency:notes:imageUri:)")));
- (void)updateBookingType:(NSString *)type confirmationNumber:(NSString * _Nullable)confirmationNumber provider:(NSString *)provider startDateTime:(NSString *)startDateTime endDateTime:(NSString * _Nullable)endDateTime timezone:(NSString *)timezone endTimezone:(NSString * _Nullable)endTimezone fromLocation:(NSString * _Nullable)fromLocation toLocation:(NSString * _Nullable)toLocation price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency notes:(NSString * _Nullable)notes imageUri:(NSString * _Nullable)imageUri id:(NSString *)id __attribute__((swift_name("updateBooking(type:confirmationNumber:provider:startDateTime:endDateTime:timezone:endTimezone:fromLocation:toLocation:price:currency:notes:imageUri:id:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Bookings")))
@interface SharedBookings : SharedBase
- (instancetype)initWithId:(NSString *)id tripId:(NSString *)tripId type:(NSString *)type confirmationNumber:(NSString * _Nullable)confirmationNumber provider:(NSString *)provider startDateTime:(NSString *)startDateTime endDateTime:(NSString * _Nullable)endDateTime timezone:(NSString *)timezone endTimezone:(NSString * _Nullable)endTimezone fromLocation:(NSString * _Nullable)fromLocation toLocation:(NSString * _Nullable)toLocation price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency notes:(NSString * _Nullable)notes imageUri:(NSString * _Nullable)imageUri __attribute__((swift_name("init(id:tripId:type:confirmationNumber:provider:startDateTime:endDateTime:timezone:endTimezone:fromLocation:toLocation:price:currency:notes:imageUri:)"))) __attribute__((objc_designated_initializer));
- (SharedBookings *)doCopyId:(NSString *)id tripId:(NSString *)tripId type:(NSString *)type confirmationNumber:(NSString * _Nullable)confirmationNumber provider:(NSString *)provider startDateTime:(NSString *)startDateTime endDateTime:(NSString * _Nullable)endDateTime timezone:(NSString *)timezone endTimezone:(NSString * _Nullable)endTimezone fromLocation:(NSString * _Nullable)fromLocation toLocation:(NSString * _Nullable)toLocation price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency notes:(NSString * _Nullable)notes imageUri:(NSString * _Nullable)imageUri __attribute__((swift_name("doCopy(id:tripId:type:confirmationNumber:provider:startDateTime:endDateTime:timezone:endTimezone:fromLocation:toLocation:price:currency:notes:imageUri:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable confirmationNumber __attribute__((swift_name("confirmationNumber")));
@property (readonly) NSString * _Nullable currency __attribute__((swift_name("currency")));
@property (readonly) NSString * _Nullable endDateTime __attribute__((swift_name("endDateTime")));
@property (readonly) NSString * _Nullable endTimezone __attribute__((swift_name("endTimezone")));
@property (readonly) NSString * _Nullable fromLocation __attribute__((swift_name("fromLocation")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable imageUri __attribute__((swift_name("imageUri")));
@property (readonly) NSString * _Nullable notes __attribute__((swift_name("notes")));
@property (readonly) SharedDouble * _Nullable price __attribute__((swift_name("price")));
@property (readonly) NSString *provider __attribute__((swift_name("provider")));
@property (readonly) NSString *startDateTime __attribute__((swift_name("startDateTime")));
@property (readonly) NSString *timezone __attribute__((swift_name("timezone")));
@property (readonly) NSString * _Nullable toLocation __attribute__((swift_name("toLocation")));
@property (readonly) NSString *tripId __attribute__((swift_name("tripId")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GapQueries")))
@interface SharedGapQueries : SharedRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)deleteGapId:(NSString *)id __attribute__((swift_name("deleteGap(id:)")));
- (void)deleteGapsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("deleteGapsByTripId(tripId:)")));
- (SharedRuntimeQuery<SharedGaps *> *)getGapByIdId:(NSString *)id __attribute__((swift_name("getGapById(id:)")));
- (SharedRuntimeQuery<id> *)getGapByIdId:(NSString *)id mapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString *, NSString *, NSString *, SharedLong *))mapper __attribute__((swift_name("getGapById(id:mapper:)")));
- (SharedRuntimeQuery<SharedGaps *> *)getGapsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("getGapsByTripId(tripId:)")));
- (SharedRuntimeQuery<id> *)getGapsByTripIdTripId:(NSString *)tripId mapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString *, NSString *, NSString *, SharedLong *))mapper __attribute__((swift_name("getGapsByTripId(tripId:mapper:)")));
- (SharedRuntimeQuery<SharedGaps *> *)getUnresolvedGapsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("getUnresolvedGapsByTripId(tripId:)")));
- (SharedRuntimeQuery<id> *)getUnresolvedGapsByTripIdTripId:(NSString *)tripId mapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString *, NSString *, NSString *, SharedLong *))mapper __attribute__((swift_name("getUnresolvedGapsByTripId(tripId:mapper:)")));
- (void)insertGapId:(NSString *)id tripId:(NSString *)tripId gapType:(NSString *)gapType fromLocationId:(NSString *)fromLocationId toLocationId:(NSString *)toLocationId fromDate:(NSString *)fromDate toDate:(NSString *)toDate isResolved:(int64_t)isResolved __attribute__((swift_name("insertGap(id:tripId:gapType:fromLocationId:toLocationId:fromDate:toDate:isResolved:)")));
- (void)markGapAsResolvedId:(NSString *)id __attribute__((swift_name("markGapAsResolved(id:)")));
- (void)updateGapGapType:(NSString *)gapType fromLocationId:(NSString *)fromLocationId toLocationId:(NSString *)toLocationId fromDate:(NSString *)fromDate toDate:(NSString *)toDate isResolved:(int64_t)isResolved id:(NSString *)id __attribute__((swift_name("updateGap(gapType:fromLocationId:toLocationId:fromDate:toDate:isResolved:id:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Gaps")))
@interface SharedGaps : SharedBase
- (instancetype)initWithId:(NSString *)id tripId:(NSString *)tripId gapType:(NSString *)gapType fromLocationId:(NSString *)fromLocationId toLocationId:(NSString *)toLocationId fromDate:(NSString *)fromDate toDate:(NSString *)toDate isResolved:(int64_t)isResolved __attribute__((swift_name("init(id:tripId:gapType:fromLocationId:toLocationId:fromDate:toDate:isResolved:)"))) __attribute__((objc_designated_initializer));
- (SharedGaps *)doCopyId:(NSString *)id tripId:(NSString *)tripId gapType:(NSString *)gapType fromLocationId:(NSString *)fromLocationId toLocationId:(NSString *)toLocationId fromDate:(NSString *)fromDate toDate:(NSString *)toDate isResolved:(int64_t)isResolved __attribute__((swift_name("doCopy(id:tripId:gapType:fromLocationId:toLocationId:fromDate:toDate:isResolved:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *fromDate __attribute__((swift_name("fromDate")));
@property (readonly) NSString *fromLocationId __attribute__((swift_name("fromLocationId")));
@property (readonly) NSString *gapType __attribute__((swift_name("gapType")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) int64_t isResolved __attribute__((swift_name("isResolved")));
@property (readonly) NSString *toDate __attribute__((swift_name("toDate")));
@property (readonly) NSString *toLocationId __attribute__((swift_name("toLocationId")));
@property (readonly) NSString *tripId __attribute__((swift_name("tripId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("LocationQueries")))
@interface SharedLocationQueries : SharedRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (SharedRuntimeQuery<SharedLong *> *)countLocationsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("countLocationsByTripId(tripId:)")));
- (void)deleteLocationId:(NSString *)id __attribute__((swift_name("deleteLocation(id:)")));
- (void)deleteLocationsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("deleteLocationsByTripId(tripId:)")));
- (SharedRuntimeQuery<SharedLocations *> *)getLocationByIdId:(NSString *)id __attribute__((swift_name("getLocationById(id:)")));
- (SharedRuntimeQuery<id> *)getLocationByIdId:(NSString *)id mapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString * _Nullable, SharedDouble * _Nullable, SharedDouble * _Nullable, SharedLong *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getLocationById(id:mapper:)")));
- (SharedRuntimeQuery<SharedLocations *> *)getLocationsByTripIdTripId:(NSString *)tripId __attribute__((swift_name("getLocationsByTripId(tripId:)")));
- (SharedRuntimeQuery<id> *)getLocationsByTripIdTripId:(NSString *)tripId mapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString * _Nullable, SharedDouble * _Nullable, SharedDouble * _Nullable, SharedLong *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable))mapper __attribute__((swift_name("getLocationsByTripId(tripId:mapper:)")));
- (void)insertLocationId:(NSString *)id tripId:(NSString *)tripId name:(NSString *)name country:(NSString *)country date:(NSString * _Nullable)date latitude:(SharedDouble * _Nullable)latitude longitude:(SharedDouble * _Nullable)longitude order:(int64_t)order timezone:(NSString * _Nullable)timezone arrivalDateTime:(NSString * _Nullable)arrivalDateTime departureDateTime:(NSString * _Nullable)departureDateTime __attribute__((swift_name("insertLocation(id:tripId:name:country:date:latitude:longitude:order:timezone:arrivalDateTime:departureDateTime:)")));
- (void)updateLocationName:(NSString *)name country:(NSString *)country date:(NSString * _Nullable)date latitude:(SharedDouble * _Nullable)latitude longitude:(SharedDouble * _Nullable)longitude order:(int64_t)order timezone:(NSString * _Nullable)timezone arrivalDateTime:(NSString * _Nullable)arrivalDateTime departureDateTime:(NSString * _Nullable)departureDateTime id:(NSString *)id __attribute__((swift_name("updateLocation(name:country:date:latitude:longitude:order:timezone:arrivalDateTime:departureDateTime:id:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Locations")))
@interface SharedLocations : SharedBase
- (instancetype)initWithId:(NSString *)id tripId:(NSString *)tripId name:(NSString *)name country:(NSString *)country date:(NSString * _Nullable)date latitude:(SharedDouble * _Nullable)latitude longitude:(SharedDouble * _Nullable)longitude order:(int64_t)order timezone:(NSString * _Nullable)timezone arrivalDateTime:(NSString * _Nullable)arrivalDateTime departureDateTime:(NSString * _Nullable)departureDateTime __attribute__((swift_name("init(id:tripId:name:country:date:latitude:longitude:order:timezone:arrivalDateTime:departureDateTime:)"))) __attribute__((objc_designated_initializer));
- (SharedLocations *)doCopyId:(NSString *)id tripId:(NSString *)tripId name:(NSString *)name country:(NSString *)country date:(NSString * _Nullable)date latitude:(SharedDouble * _Nullable)latitude longitude:(SharedDouble * _Nullable)longitude order:(int64_t)order timezone:(NSString * _Nullable)timezone arrivalDateTime:(NSString * _Nullable)arrivalDateTime departureDateTime:(NSString * _Nullable)departureDateTime __attribute__((swift_name("doCopy(id:tripId:name:country:date:latitude:longitude:order:timezone:arrivalDateTime:departureDateTime:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable arrivalDateTime __attribute__((swift_name("arrivalDateTime")));
@property (readonly) NSString *country __attribute__((swift_name("country")));
@property (readonly) NSString * _Nullable date __attribute__((swift_name("date")));
@property (readonly) NSString * _Nullable departureDateTime __attribute__((swift_name("departureDateTime")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) SharedDouble * _Nullable latitude __attribute__((swift_name("latitude")));
@property (readonly) SharedDouble * _Nullable longitude __attribute__((swift_name("longitude")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int64_t order __attribute__((swift_name("order")));
@property (readonly) NSString * _Nullable timezone __attribute__((swift_name("timezone")));
@property (readonly) NSString *tripId __attribute__((swift_name("tripId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TransitOptionQueries")))
@interface SharedTransitOptionQueries : SharedRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)deleteTransitOptionId:(NSString *)id __attribute__((swift_name("deleteTransitOption(id:)")));
- (void)deleteTransitOptionsByGapIdGapId:(NSString *)gapId __attribute__((swift_name("deleteTransitOptionsByGapId(gapId:)")));
- (SharedRuntimeQuery<SharedTransit_options *> *)getTransitOptionByIdId:(NSString *)id __attribute__((swift_name("getTransitOptionById(id:)")));
- (SharedRuntimeQuery<id> *)getTransitOptionByIdId:(NSString *)id mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, SharedLong *, SharedDouble * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedLong *))mapper __attribute__((swift_name("getTransitOptionById(id:mapper:)")));
- (SharedRuntimeQuery<SharedTransit_options *> *)getTransitOptionsByGapIdGapId:(NSString *)gapId __attribute__((swift_name("getTransitOptionsByGapId(gapId:)")));
- (SharedRuntimeQuery<id> *)getTransitOptionsByGapIdGapId:(NSString *)gapId mapper:(id (^)(NSString *, NSString *, NSString *, NSString * _Nullable, SharedLong *, SharedDouble * _Nullable, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedLong *))mapper __attribute__((swift_name("getTransitOptionsByGapId(gapId:mapper:)")));
- (void)insertTransitOptionId:(NSString *)id gapId:(NSString *)gapId mode:(NSString *)mode provider:(NSString * _Nullable)provider duration:(int64_t)duration price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency departureTime:(NSString * _Nullable)departureTime arrivalTime:(NSString * _Nullable)arrivalTime fetchedAt:(int64_t)fetchedAt __attribute__((swift_name("insertTransitOption(id:gapId:mode:provider:duration:price:currency:departureTime:arrivalTime:fetchedAt:)")));
- (void)updateTransitOptionMode:(NSString *)mode provider:(NSString * _Nullable)provider duration:(int64_t)duration price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency departureTime:(NSString * _Nullable)departureTime arrivalTime:(NSString * _Nullable)arrivalTime fetchedAt:(int64_t)fetchedAt id:(NSString *)id __attribute__((swift_name("updateTransitOption(mode:provider:duration:price:currency:departureTime:arrivalTime:fetchedAt:id:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Transit_options")))
@interface SharedTransit_options : SharedBase
- (instancetype)initWithId:(NSString *)id gapId:(NSString *)gapId mode:(NSString *)mode provider:(NSString * _Nullable)provider duration:(int64_t)duration price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency departureTime:(NSString * _Nullable)departureTime arrivalTime:(NSString * _Nullable)arrivalTime fetchedAt:(int64_t)fetchedAt __attribute__((swift_name("init(id:gapId:mode:provider:duration:price:currency:departureTime:arrivalTime:fetchedAt:)"))) __attribute__((objc_designated_initializer));
- (SharedTransit_options *)doCopyId:(NSString *)id gapId:(NSString *)gapId mode:(NSString *)mode provider:(NSString * _Nullable)provider duration:(int64_t)duration price:(SharedDouble * _Nullable)price currency:(NSString * _Nullable)currency departureTime:(NSString * _Nullable)departureTime arrivalTime:(NSString * _Nullable)arrivalTime fetchedAt:(int64_t)fetchedAt __attribute__((swift_name("doCopy(id:gapId:mode:provider:duration:price:currency:departureTime:arrivalTime:fetchedAt:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable arrivalTime __attribute__((swift_name("arrivalTime")));
@property (readonly) NSString * _Nullable currency __attribute__((swift_name("currency")));
@property (readonly) NSString * _Nullable departureTime __attribute__((swift_name("departureTime")));
@property (readonly) int64_t duration __attribute__((swift_name("duration")));
@property (readonly) int64_t fetchedAt __attribute__((swift_name("fetchedAt")));
@property (readonly) NSString *gapId __attribute__((swift_name("gapId")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *mode __attribute__((swift_name("mode")));
@property (readonly) SharedDouble * _Nullable price __attribute__((swift_name("price")));
@property (readonly) NSString * _Nullable provider __attribute__((swift_name("provider")));
@end

__attribute__((swift_name("TravlogueDatabase")))
@protocol SharedTravlogueDatabase <SharedRuntimeTransacter>
@required
@property (readonly) SharedActivityQueries *activityQueries __attribute__((swift_name("activityQueries")));
@property (readonly) SharedBookingQueries *bookingQueries __attribute__((swift_name("bookingQueries")));
@property (readonly) SharedGapQueries *gapQueries __attribute__((swift_name("gapQueries")));
@property (readonly) SharedLocationQueries *locationQueries __attribute__((swift_name("locationQueries")));
@property (readonly) SharedTransitOptionQueries *transitOptionQueries __attribute__((swift_name("transitOptionQueries")));
@property (readonly) SharedTripQueries *tripQueries __attribute__((swift_name("tripQueries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TravlogueDatabaseCompanion")))
@interface SharedTravlogueDatabaseCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedTravlogueDatabaseCompanion *shared __attribute__((swift_name("shared")));
- (id<SharedTravlogueDatabase>)invokeDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("invoke(driver:)")));
@property (readonly) id<SharedRuntimeSqlSchema> Schema __attribute__((swift_name("Schema")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TripQueries")))
@interface SharedTripQueries : SharedRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (SharedRuntimeQuery<SharedLong *> *)countTrips __attribute__((swift_name("countTrips()")));
- (void)deleteTripId:(NSString *)id __attribute__((swift_name("deleteTrip(id:)")));
- (SharedRuntimeQuery<SharedTrips *> *)getAllTrips __attribute__((swift_name("getAllTrips()")));
- (SharedRuntimeQuery<id> *)getAllTripsMapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedLong * _Nullable, SharedLong *, SharedLong *))mapper __attribute__((swift_name("getAllTrips(mapper:)")));
- (SharedRuntimeQuery<SharedTrips *> *)getTripByIdId:(NSString *)id __attribute__((swift_name("getTripById(id:)")));
- (SharedRuntimeQuery<id> *)getTripByIdId:(NSString *)id mapper:(id (^)(NSString *, NSString *, NSString *, NSString *, NSString * _Nullable, NSString * _Nullable, NSString * _Nullable, SharedLong * _Nullable, SharedLong *, SharedLong *))mapper __attribute__((swift_name("getTripById(id:mapper:)")));
- (void)insertTripId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity dateType:(NSString *)dateType startDate:(NSString * _Nullable)startDate endDate:(NSString * _Nullable)endDate flexibleMonth:(NSString * _Nullable)flexibleMonth flexibleDuration:(SharedLong * _Nullable)flexibleDuration createdAt:(int64_t)createdAt updatedAt:(int64_t)updatedAt __attribute__((swift_name("insertTrip(id:name:originCity:dateType:startDate:endDate:flexibleMonth:flexibleDuration:createdAt:updatedAt:)")));
- (void)updateTripName:(NSString *)name originCity:(NSString *)originCity dateType:(NSString *)dateType startDate:(NSString * _Nullable)startDate endDate:(NSString * _Nullable)endDate flexibleMonth:(NSString * _Nullable)flexibleMonth flexibleDuration:(SharedLong * _Nullable)flexibleDuration updatedAt:(int64_t)updatedAt id:(NSString *)id __attribute__((swift_name("updateTrip(name:originCity:dateType:startDate:endDate:flexibleMonth:flexibleDuration:updatedAt:id:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Trips")))
@interface SharedTrips : SharedBase
- (instancetype)initWithId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity dateType:(NSString *)dateType startDate:(NSString * _Nullable)startDate endDate:(NSString * _Nullable)endDate flexibleMonth:(NSString * _Nullable)flexibleMonth flexibleDuration:(SharedLong * _Nullable)flexibleDuration createdAt:(int64_t)createdAt updatedAt:(int64_t)updatedAt __attribute__((swift_name("init(id:name:originCity:dateType:startDate:endDate:flexibleMonth:flexibleDuration:createdAt:updatedAt:)"))) __attribute__((objc_designated_initializer));
- (SharedTrips *)doCopyId:(NSString *)id name:(NSString *)name originCity:(NSString *)originCity dateType:(NSString *)dateType startDate:(NSString * _Nullable)startDate endDate:(NSString * _Nullable)endDate flexibleMonth:(NSString * _Nullable)flexibleMonth flexibleDuration:(SharedLong * _Nullable)flexibleDuration createdAt:(int64_t)createdAt updatedAt:(int64_t)updatedAt __attribute__((swift_name("doCopy(id:name:originCity:dateType:startDate:endDate:flexibleMonth:flexibleDuration:createdAt:updatedAt:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t createdAt __attribute__((swift_name("createdAt")));
@property (readonly) NSString *dateType __attribute__((swift_name("dateType")));
@property (readonly) NSString * _Nullable endDate __attribute__((swift_name("endDate")));
@property (readonly) SharedLong * _Nullable flexibleDuration __attribute__((swift_name("flexibleDuration")));
@property (readonly) NSString * _Nullable flexibleMonth __attribute__((swift_name("flexibleMonth")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *originCity __attribute__((swift_name("originCity")));
@property (readonly) NSString * _Nullable startDate __attribute__((swift_name("startDate")));
@property (readonly) int64_t updatedAt __attribute__((swift_name("updatedAt")));
@end


/**
 * UI Events for Create Trip screen
 */
__attribute__((swift_name("CreateTripUiEvent")))
@interface SharedCreateTripUiEvent : SharedBase
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateTripUiEvent.NavigateBack")))
@interface SharedCreateTripUiEventNavigateBack : SharedCreateTripUiEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)navigateBack __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedCreateTripUiEventNavigateBack *shared __attribute__((swift_name("shared")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateTripUiEvent.ShowError")))
@interface SharedCreateTripUiEventShowError : SharedCreateTripUiEvent
- (instancetype)initWithMessage:(NSString *)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (SharedCreateTripUiEventShowError *)doCopyMessage:(NSString *)message __attribute__((swift_name("doCopy(message:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *message __attribute__((swift_name("message")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateTripUiEvent.TripCreatedSuccess")))
@interface SharedCreateTripUiEventTripCreatedSuccess : SharedCreateTripUiEvent
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)tripCreatedSuccess __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedCreateTripUiEventTripCreatedSuccess *shared __attribute__((swift_name("shared")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end


/**
 * UI State for Create Trip screen
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateTripUiState")))
@interface SharedCreateTripUiState : SharedBase
- (instancetype)initWithTripName:(NSString *)tripName originCity:(NSString *)originCity selectedDateType:(SharedDateType *)selectedDateType startDate:(SharedKotlinx_datetimeLocalDate * _Nullable)startDate endDate:(SharedKotlinx_datetimeLocalDate * _Nullable)endDate flexibleMonth:(NSString *)flexibleMonth flexibleDuration:(NSString *)flexibleDuration tripNameError:(NSString * _Nullable)tripNameError originCityError:(NSString * _Nullable)originCityError dateError:(NSString * _Nullable)dateError isLoading:(BOOL)isLoading isSuccess:(BOOL)isSuccess errorMessage:(NSString * _Nullable)errorMessage __attribute__((swift_name("init(tripName:originCity:selectedDateType:startDate:endDate:flexibleMonth:flexibleDuration:tripNameError:originCityError:dateError:isLoading:isSuccess:errorMessage:)"))) __attribute__((objc_designated_initializer));
- (SharedCreateTripUiState *)doCopyTripName:(NSString *)tripName originCity:(NSString *)originCity selectedDateType:(SharedDateType *)selectedDateType startDate:(SharedKotlinx_datetimeLocalDate * _Nullable)startDate endDate:(SharedKotlinx_datetimeLocalDate * _Nullable)endDate flexibleMonth:(NSString *)flexibleMonth flexibleDuration:(NSString *)flexibleDuration tripNameError:(NSString * _Nullable)tripNameError originCityError:(NSString * _Nullable)originCityError dateError:(NSString * _Nullable)dateError isLoading:(BOOL)isLoading isSuccess:(BOOL)isSuccess errorMessage:(NSString * _Nullable)errorMessage __attribute__((swift_name("doCopy(tripName:originCity:selectedDateType:startDate:endDate:flexibleMonth:flexibleDuration:tripNameError:originCityError:dateError:isLoading:isSuccess:errorMessage:)")));

/**
 * UI State for Create Trip screen
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * UI State for Create Trip screen
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * UI State for Create Trip screen
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable dateError __attribute__((swift_name("dateError")));
@property (readonly) SharedKotlinx_datetimeLocalDate * _Nullable endDate __attribute__((swift_name("endDate")));
@property (readonly) NSString * _Nullable errorMessage __attribute__((swift_name("errorMessage")));
@property (readonly) NSString *flexibleDuration __attribute__((swift_name("flexibleDuration")));
@property (readonly) NSString *flexibleMonth __attribute__((swift_name("flexibleMonth")));
@property (readonly) BOOL isLoading __attribute__((swift_name("isLoading")));
@property (readonly) BOOL isSuccess __attribute__((swift_name("isSuccess")));
@property (readonly) NSString *originCity __attribute__((swift_name("originCity")));
@property (readonly) NSString * _Nullable originCityError __attribute__((swift_name("originCityError")));
@property (readonly) SharedDateType *selectedDateType __attribute__((swift_name("selectedDateType")));
@property (readonly) SharedKotlinx_datetimeLocalDate * _Nullable startDate __attribute__((swift_name("startDate")));
@property (readonly) NSString *tripName __attribute__((swift_name("tripName")));
@property (readonly) NSString * _Nullable tripNameError __attribute__((swift_name("tripNameError")));
@end

__attribute__((swift_name("Lifecycle_viewmodelViewModel")))
@interface SharedLifecycle_viewmodelViewModel : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithCloseables:(SharedKotlinArray<id<SharedKotlinAutoCloseable>> *)closeables __attribute__((swift_name("init(closeables:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithViewModelScope:(id<SharedKotlinx_coroutines_coreCoroutineScope>)viewModelScope __attribute__((swift_name("init(viewModelScope:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithViewModelScope:(id<SharedKotlinx_coroutines_coreCoroutineScope>)viewModelScope closeables:(SharedKotlinArray<id<SharedKotlinAutoCloseable>> *)closeables __attribute__((swift_name("init(viewModelScope:closeables:)"))) __attribute__((objc_designated_initializer));
- (void)addCloseableCloseable:(id<SharedKotlinAutoCloseable>)closeable __attribute__((swift_name("addCloseable(closeable:)")));
- (void)addCloseableKey:(NSString *)key closeable:(id<SharedKotlinAutoCloseable>)closeable __attribute__((swift_name("addCloseable(key:closeable:)")));
- (id<SharedKotlinAutoCloseable> _Nullable)getCloseableKey:(NSString *)key __attribute__((swift_name("getCloseable(key:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)onCleared __attribute__((swift_name("onCleared()")));
@end


/**
 * CreateTripViewModel - KMP version
 * Manages the create trip screen state
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateTripViewModel")))
@interface SharedCreateTripViewModel : SharedLifecycle_viewmodelViewModel
- (instancetype)initWithTripRepository:(SharedTripRepository *)tripRepository __attribute__((swift_name("init(tripRepository:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithCloseables:(SharedKotlinArray<id<SharedKotlinAutoCloseable>> *)closeables __attribute__((swift_name("init(closeables:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithViewModelScope:(id<SharedKotlinx_coroutines_coreCoroutineScope>)viewModelScope __attribute__((swift_name("init(viewModelScope:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithViewModelScope:(id<SharedKotlinx_coroutines_coreCoroutineScope>)viewModelScope closeables:(SharedKotlinArray<id<SharedKotlinAutoCloseable>> *)closeables __attribute__((swift_name("init(viewModelScope:closeables:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (void)onBackPressed __attribute__((swift_name("onBackPressed()")));
- (void)onCreateTripClicked __attribute__((swift_name("onCreateTripClicked()")));
- (void)onDateTypeSelectedDateType:(SharedDateType *)dateType __attribute__((swift_name("onDateTypeSelected(dateType:)")));
- (void)onEndDateSelectedDate:(SharedKotlinx_datetimeLocalDate *)date __attribute__((swift_name("onEndDateSelected(date:)")));
- (void)onFlexibleDurationChangedDuration:(NSString *)duration __attribute__((swift_name("onFlexibleDurationChanged(duration:)")));
- (void)onFlexibleMonthChangedMonth:(NSString *)month __attribute__((swift_name("onFlexibleMonthChanged(month:)")));
- (void)onOriginCityChangedCity:(NSString *)city __attribute__((swift_name("onOriginCityChanged(city:)")));
- (void)onStartDateSelectedDate:(SharedKotlinx_datetimeLocalDate *)date __attribute__((swift_name("onStartDateSelected(date:)")));
- (void)onTripNameChangedName:(NSString *)name __attribute__((swift_name("onTripNameChanged(name:)")));
@property (readonly) id<SharedKotlinx_coroutines_coreSharedFlow> uiEvents __attribute__((swift_name("uiEvents")));
@property (readonly) id<SharedKotlinx_coroutines_coreStateFlow> uiState __attribute__((swift_name("uiState")));
@end


/**
 * UI State for Home screen
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HomeUiState")))
@interface SharedHomeUiState : SharedBase
- (instancetype)initWithTrips:(NSArray<SharedTrip *> *)trips isLoading:(BOOL)isLoading error:(NSString * _Nullable)error __attribute__((swift_name("init(trips:isLoading:error:)"))) __attribute__((objc_designated_initializer));
- (SharedHomeUiState *)doCopyTrips:(NSArray<SharedTrip *> *)trips isLoading:(BOOL)isLoading error:(NSString * _Nullable)error __attribute__((swift_name("doCopy(trips:isLoading:error:)")));

/**
 * UI State for Home screen
 */
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));

/**
 * UI State for Home screen
 */
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * UI State for Home screen
 */
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable error __attribute__((swift_name("error")));
@property (readonly) BOOL isLoading __attribute__((swift_name("isLoading")));
@property (readonly) NSArray<SharedTrip *> *trips __attribute__((swift_name("trips")));
@end


/**
 * HomeViewModel - KMP version
 * Manages the home screen state and trip list
 */
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HomeViewModel")))
@interface SharedHomeViewModel : SharedLifecycle_viewmodelViewModel
- (instancetype)initWithTripRepository:(SharedTripRepository *)tripRepository __attribute__((swift_name("init(tripRepository:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithCloseables:(SharedKotlinArray<id<SharedKotlinAutoCloseable>> *)closeables __attribute__((swift_name("init(closeables:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithViewModelScope:(id<SharedKotlinx_coroutines_coreCoroutineScope>)viewModelScope __attribute__((swift_name("init(viewModelScope:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithViewModelScope:(id<SharedKotlinx_coroutines_coreCoroutineScope>)viewModelScope closeables:(SharedKotlinArray<id<SharedKotlinAutoCloseable>> *)closeables __attribute__((swift_name("init(viewModelScope:closeables:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (void)deleteTripTrip:(SharedTrip *)trip __attribute__((swift_name("deleteTrip(trip:)")));
- (void)deleteTripByIdTripId:(NSString *)tripId __attribute__((swift_name("deleteTripById(tripId:)")));
@property (readonly) id<SharedKotlinx_coroutines_coreStateFlow> allTrips __attribute__((swift_name("allTrips")));
@property (readonly) id<SharedKotlinx_coroutines_coreStateFlow> uiState __attribute__((swift_name("uiState")));
@end

@interface SharedActivity (Extensions)
- (SharedActivities *)toDbModel __attribute__((swift_name("toDbModel()")));
@end

@interface SharedBooking (Extensions)
- (SharedBookings *)toDbModel __attribute__((swift_name("toDbModel()")));
@end

@interface SharedGap (Extensions)
- (SharedGaps *)toDbModel __attribute__((swift_name("toDbModel()")));
@end

@interface SharedLocation (Extensions)
- (SharedLocations *)toDbModel __attribute__((swift_name("toDbModel()")));
@end

@interface SharedTransitOption (Extensions)
- (SharedTransit_options *)toDbModel __attribute__((swift_name("toDbModel()")));
@end

@interface SharedTrip (Extensions)
- (SharedTrips *)toDbModel __attribute__((swift_name("toDbModel()")));
@end

@interface SharedActivities (Extensions)
- (SharedActivity *)toDomainModel __attribute__((swift_name("toDomainModel()")));
@end

@interface SharedBookings (Extensions)
- (SharedBooking *)toDomainModel __attribute__((swift_name("toDomainModel()")));
@end

@interface SharedGaps (Extensions)
- (SharedGap *)toDomainModel __attribute__((swift_name("toDomainModel()")));
@end

@interface SharedLocations (Extensions)
- (SharedLocation *)toDomainModel __attribute__((swift_name("toDomainModel()")));
@end

@interface SharedTransit_options (Extensions)
- (SharedTransitOption *)toDomainModel __attribute__((swift_name("toDomainModel()")));
@end

@interface SharedTrips (Extensions)

/**
 * Extension functions to map between SQLDelight database types and domain models
 */
- (SharedTrip *)toDomainModel __attribute__((swift_name("toDomainModel()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ColorKt")))
@interface SharedColorKt : SharedBase
@property (class, readonly) uint64_t backgroundDark __attribute__((swift_name("backgroundDark")));
@property (class, readonly) uint64_t backgroundDarkHighContrast __attribute__((swift_name("backgroundDarkHighContrast")));
@property (class, readonly) uint64_t backgroundDarkMediumContrast __attribute__((swift_name("backgroundDarkMediumContrast")));
@property (class, readonly) uint64_t backgroundLight __attribute__((swift_name("backgroundLight")));
@property (class, readonly) uint64_t backgroundLightHighContrast __attribute__((swift_name("backgroundLightHighContrast")));
@property (class, readonly) uint64_t backgroundLightMediumContrast __attribute__((swift_name("backgroundLightMediumContrast")));
@property (class, readonly) uint64_t errorContainerDark __attribute__((swift_name("errorContainerDark")));
@property (class, readonly) uint64_t errorContainerDarkHighContrast __attribute__((swift_name("errorContainerDarkHighContrast")));
@property (class, readonly) uint64_t errorContainerDarkMediumContrast __attribute__((swift_name("errorContainerDarkMediumContrast")));
@property (class, readonly) uint64_t errorContainerLight __attribute__((swift_name("errorContainerLight")));
@property (class, readonly) uint64_t errorContainerLightHighContrast __attribute__((swift_name("errorContainerLightHighContrast")));
@property (class, readonly) uint64_t errorContainerLightMediumContrast __attribute__((swift_name("errorContainerLightMediumContrast")));
@property (class, readonly) uint64_t errorDark __attribute__((swift_name("errorDark")));
@property (class, readonly) uint64_t errorDarkHighContrast __attribute__((swift_name("errorDarkHighContrast")));
@property (class, readonly) uint64_t errorDarkMediumContrast __attribute__((swift_name("errorDarkMediumContrast")));
@property (class, readonly) uint64_t errorLight __attribute__((swift_name("errorLight")));
@property (class, readonly) uint64_t errorLightHighContrast __attribute__((swift_name("errorLightHighContrast")));
@property (class, readonly) uint64_t errorLightMediumContrast __attribute__((swift_name("errorLightMediumContrast")));
@property (class, readonly) uint64_t inverseOnSurfaceDark __attribute__((swift_name("inverseOnSurfaceDark")));
@property (class, readonly) uint64_t inverseOnSurfaceDarkHighContrast __attribute__((swift_name("inverseOnSurfaceDarkHighContrast")));
@property (class, readonly) uint64_t inverseOnSurfaceDarkMediumContrast __attribute__((swift_name("inverseOnSurfaceDarkMediumContrast")));
@property (class, readonly) uint64_t inverseOnSurfaceLight __attribute__((swift_name("inverseOnSurfaceLight")));
@property (class, readonly) uint64_t inverseOnSurfaceLightHighContrast __attribute__((swift_name("inverseOnSurfaceLightHighContrast")));
@property (class, readonly) uint64_t inverseOnSurfaceLightMediumContrast __attribute__((swift_name("inverseOnSurfaceLightMediumContrast")));
@property (class, readonly) uint64_t inversePrimaryDark __attribute__((swift_name("inversePrimaryDark")));
@property (class, readonly) uint64_t inversePrimaryDarkHighContrast __attribute__((swift_name("inversePrimaryDarkHighContrast")));
@property (class, readonly) uint64_t inversePrimaryDarkMediumContrast __attribute__((swift_name("inversePrimaryDarkMediumContrast")));
@property (class, readonly) uint64_t inversePrimaryLight __attribute__((swift_name("inversePrimaryLight")));
@property (class, readonly) uint64_t inversePrimaryLightHighContrast __attribute__((swift_name("inversePrimaryLightHighContrast")));
@property (class, readonly) uint64_t inversePrimaryLightMediumContrast __attribute__((swift_name("inversePrimaryLightMediumContrast")));
@property (class, readonly) uint64_t inverseSurfaceDark __attribute__((swift_name("inverseSurfaceDark")));
@property (class, readonly) uint64_t inverseSurfaceDarkHighContrast __attribute__((swift_name("inverseSurfaceDarkHighContrast")));
@property (class, readonly) uint64_t inverseSurfaceDarkMediumContrast __attribute__((swift_name("inverseSurfaceDarkMediumContrast")));
@property (class, readonly) uint64_t inverseSurfaceLight __attribute__((swift_name("inverseSurfaceLight")));
@property (class, readonly) uint64_t inverseSurfaceLightHighContrast __attribute__((swift_name("inverseSurfaceLightHighContrast")));
@property (class, readonly) uint64_t inverseSurfaceLightMediumContrast __attribute__((swift_name("inverseSurfaceLightMediumContrast")));
@property (class, readonly) uint64_t md_amber_100 __attribute__((swift_name("md_amber_100")));
@property (class, readonly) uint64_t md_amber_200 __attribute__((swift_name("md_amber_200")));
@property (class, readonly) uint64_t md_amber_300 __attribute__((swift_name("md_amber_300")));
@property (class, readonly) uint64_t md_amber_400 __attribute__((swift_name("md_amber_400")));
@property (class, readonly) uint64_t md_amber_50 __attribute__((swift_name("md_amber_50")));
@property (class, readonly) uint64_t md_amber_500 __attribute__((swift_name("md_amber_500")));
@property (class, readonly) uint64_t md_amber_600 __attribute__((swift_name("md_amber_600")));
@property (class, readonly) uint64_t md_amber_700 __attribute__((swift_name("md_amber_700")));
@property (class, readonly) uint64_t md_amber_800 __attribute__((swift_name("md_amber_800")));
@property (class, readonly) uint64_t md_amber_900 __attribute__((swift_name("md_amber_900")));
@property (class, readonly) uint64_t md_amber_A100 __attribute__((swift_name("md_amber_A100")));
@property (class, readonly) uint64_t md_amber_A200 __attribute__((swift_name("md_amber_A200")));
@property (class, readonly) uint64_t md_amber_A400 __attribute__((swift_name("md_amber_A400")));
@property (class, readonly) uint64_t md_amber_A700 __attribute__((swift_name("md_amber_A700")));
@property (class, readonly) uint64_t md_black_1000 __attribute__((swift_name("md_black_1000")));
@property (class, readonly) uint64_t md_black_trans_60 __attribute__((swift_name("md_black_trans_60")));
@property (class, readonly) uint64_t md_blue_100 __attribute__((swift_name("md_blue_100")));
@property (class, readonly) uint64_t md_blue_200 __attribute__((swift_name("md_blue_200")));
@property (class, readonly) uint64_t md_blue_300 __attribute__((swift_name("md_blue_300")));
@property (class, readonly) uint64_t md_blue_400 __attribute__((swift_name("md_blue_400")));
@property (class, readonly) uint64_t md_blue_50 __attribute__((swift_name("md_blue_50")));
@property (class, readonly) uint64_t md_blue_500 __attribute__((swift_name("md_blue_500")));
@property (class, readonly) uint64_t md_blue_600 __attribute__((swift_name("md_blue_600")));
@property (class, readonly) uint64_t md_blue_700 __attribute__((swift_name("md_blue_700")));
@property (class, readonly) uint64_t md_blue_800 __attribute__((swift_name("md_blue_800")));
@property (class, readonly) uint64_t md_blue_900 __attribute__((swift_name("md_blue_900")));
@property (class, readonly) uint64_t md_blue_A100 __attribute__((swift_name("md_blue_A100")));
@property (class, readonly) uint64_t md_blue_A200 __attribute__((swift_name("md_blue_A200")));
@property (class, readonly) uint64_t md_blue_A400 __attribute__((swift_name("md_blue_A400")));
@property (class, readonly) uint64_t md_blue_A700 __attribute__((swift_name("md_blue_A700")));
@property (class, readonly) uint64_t md_blue_grey_100 __attribute__((swift_name("md_blue_grey_100")));
@property (class, readonly) uint64_t md_blue_grey_200 __attribute__((swift_name("md_blue_grey_200")));
@property (class, readonly) uint64_t md_blue_grey_300 __attribute__((swift_name("md_blue_grey_300")));
@property (class, readonly) uint64_t md_blue_grey_400 __attribute__((swift_name("md_blue_grey_400")));
@property (class, readonly) uint64_t md_blue_grey_50 __attribute__((swift_name("md_blue_grey_50")));
@property (class, readonly) uint64_t md_blue_grey_500 __attribute__((swift_name("md_blue_grey_500")));
@property (class, readonly) uint64_t md_blue_grey_600 __attribute__((swift_name("md_blue_grey_600")));
@property (class, readonly) uint64_t md_blue_grey_700 __attribute__((swift_name("md_blue_grey_700")));
@property (class, readonly) uint64_t md_blue_grey_800 __attribute__((swift_name("md_blue_grey_800")));
@property (class, readonly) uint64_t md_blue_grey_900 __attribute__((swift_name("md_blue_grey_900")));
@property (class, readonly) uint64_t md_brown_100 __attribute__((swift_name("md_brown_100")));
@property (class, readonly) uint64_t md_brown_200 __attribute__((swift_name("md_brown_200")));
@property (class, readonly) uint64_t md_brown_300 __attribute__((swift_name("md_brown_300")));
@property (class, readonly) uint64_t md_brown_400 __attribute__((swift_name("md_brown_400")));
@property (class, readonly) uint64_t md_brown_50 __attribute__((swift_name("md_brown_50")));
@property (class, readonly) uint64_t md_brown_500 __attribute__((swift_name("md_brown_500")));
@property (class, readonly) uint64_t md_brown_600 __attribute__((swift_name("md_brown_600")));
@property (class, readonly) uint64_t md_brown_700 __attribute__((swift_name("md_brown_700")));
@property (class, readonly) uint64_t md_brown_800 __attribute__((swift_name("md_brown_800")));
@property (class, readonly) uint64_t md_brown_900 __attribute__((swift_name("md_brown_900")));
@property (class, readonly) uint64_t md_cyan_100 __attribute__((swift_name("md_cyan_100")));
@property (class, readonly) uint64_t md_cyan_200 __attribute__((swift_name("md_cyan_200")));
@property (class, readonly) uint64_t md_cyan_300 __attribute__((swift_name("md_cyan_300")));
@property (class, readonly) uint64_t md_cyan_400 __attribute__((swift_name("md_cyan_400")));
@property (class, readonly) uint64_t md_cyan_50 __attribute__((swift_name("md_cyan_50")));
@property (class, readonly) uint64_t md_cyan_500 __attribute__((swift_name("md_cyan_500")));
@property (class, readonly) uint64_t md_cyan_600 __attribute__((swift_name("md_cyan_600")));
@property (class, readonly) uint64_t md_cyan_700 __attribute__((swift_name("md_cyan_700")));
@property (class, readonly) uint64_t md_cyan_800 __attribute__((swift_name("md_cyan_800")));
@property (class, readonly) uint64_t md_cyan_900 __attribute__((swift_name("md_cyan_900")));
@property (class, readonly) uint64_t md_cyan_A100 __attribute__((swift_name("md_cyan_A100")));
@property (class, readonly) uint64_t md_cyan_A200 __attribute__((swift_name("md_cyan_A200")));
@property (class, readonly) uint64_t md_cyan_A400 __attribute__((swift_name("md_cyan_A400")));
@property (class, readonly) uint64_t md_cyan_A700 __attribute__((swift_name("md_cyan_A700")));
@property (class, readonly) uint64_t md_deep_orange_100 __attribute__((swift_name("md_deep_orange_100")));
@property (class, readonly) uint64_t md_deep_orange_200 __attribute__((swift_name("md_deep_orange_200")));
@property (class, readonly) uint64_t md_deep_orange_300 __attribute__((swift_name("md_deep_orange_300")));
@property (class, readonly) uint64_t md_deep_orange_40 __attribute__((swift_name("md_deep_orange_40")));
@property (class, readonly) uint64_t md_deep_orange_400 __attribute__((swift_name("md_deep_orange_400")));
@property (class, readonly) uint64_t md_deep_orange_45 __attribute__((swift_name("md_deep_orange_45")));
@property (class, readonly) uint64_t md_deep_orange_50 __attribute__((swift_name("md_deep_orange_50")));
@property (class, readonly) uint64_t md_deep_orange_500 __attribute__((swift_name("md_deep_orange_500")));
@property (class, readonly) uint64_t md_deep_orange_600 __attribute__((swift_name("md_deep_orange_600")));
@property (class, readonly) uint64_t md_deep_orange_700 __attribute__((swift_name("md_deep_orange_700")));
@property (class, readonly) uint64_t md_deep_orange_800 __attribute__((swift_name("md_deep_orange_800")));
@property (class, readonly) uint64_t md_deep_orange_900 __attribute__((swift_name("md_deep_orange_900")));
@property (class, readonly) uint64_t md_deep_orange_A100 __attribute__((swift_name("md_deep_orange_A100")));
@property (class, readonly) uint64_t md_deep_orange_A200 __attribute__((swift_name("md_deep_orange_A200")));
@property (class, readonly) uint64_t md_deep_orange_A400 __attribute__((swift_name("md_deep_orange_A400")));
@property (class, readonly) uint64_t md_deep_orange_A700 __attribute__((swift_name("md_deep_orange_A700")));
@property (class, readonly) uint64_t md_deep_purple_100 __attribute__((swift_name("md_deep_purple_100")));
@property (class, readonly) uint64_t md_deep_purple_200 __attribute__((swift_name("md_deep_purple_200")));
@property (class, readonly) uint64_t md_deep_purple_300 __attribute__((swift_name("md_deep_purple_300")));
@property (class, readonly) uint64_t md_deep_purple_400 __attribute__((swift_name("md_deep_purple_400")));
@property (class, readonly) uint64_t md_deep_purple_50 __attribute__((swift_name("md_deep_purple_50")));
@property (class, readonly) uint64_t md_deep_purple_500 __attribute__((swift_name("md_deep_purple_500")));
@property (class, readonly) uint64_t md_deep_purple_600 __attribute__((swift_name("md_deep_purple_600")));
@property (class, readonly) uint64_t md_deep_purple_700 __attribute__((swift_name("md_deep_purple_700")));
@property (class, readonly) uint64_t md_deep_purple_800 __attribute__((swift_name("md_deep_purple_800")));
@property (class, readonly) uint64_t md_deep_purple_900 __attribute__((swift_name("md_deep_purple_900")));
@property (class, readonly) uint64_t md_deep_purple_A100 __attribute__((swift_name("md_deep_purple_A100")));
@property (class, readonly) uint64_t md_deep_purple_A200 __attribute__((swift_name("md_deep_purple_A200")));
@property (class, readonly) uint64_t md_deep_purple_A400 __attribute__((swift_name("md_deep_purple_A400")));
@property (class, readonly) uint64_t md_deep_purple_A700 __attribute__((swift_name("md_deep_purple_A700")));
@property (class, readonly) uint64_t md_green_100 __attribute__((swift_name("md_green_100")));
@property (class, readonly) uint64_t md_green_200 __attribute__((swift_name("md_green_200")));
@property (class, readonly) uint64_t md_green_300 __attribute__((swift_name("md_green_300")));
@property (class, readonly) uint64_t md_green_400 __attribute__((swift_name("md_green_400")));
@property (class, readonly) uint64_t md_green_50 __attribute__((swift_name("md_green_50")));
@property (class, readonly) uint64_t md_green_500 __attribute__((swift_name("md_green_500")));
@property (class, readonly) uint64_t md_green_600 __attribute__((swift_name("md_green_600")));
@property (class, readonly) uint64_t md_green_700 __attribute__((swift_name("md_green_700")));
@property (class, readonly) uint64_t md_green_750 __attribute__((swift_name("md_green_750")));
@property (class, readonly) uint64_t md_green_800 __attribute__((swift_name("md_green_800")));
@property (class, readonly) uint64_t md_green_900 __attribute__((swift_name("md_green_900")));
@property (class, readonly) uint64_t md_green_A100 __attribute__((swift_name("md_green_A100")));
@property (class, readonly) uint64_t md_green_A200 __attribute__((swift_name("md_green_A200")));
@property (class, readonly) uint64_t md_green_A400 __attribute__((swift_name("md_green_A400")));
@property (class, readonly) uint64_t md_green_A700 __attribute__((swift_name("md_green_A700")));
@property (class, readonly) uint64_t md_grey_100 __attribute__((swift_name("md_grey_100")));
@property (class, readonly) uint64_t md_grey_200 __attribute__((swift_name("md_grey_200")));
@property (class, readonly) uint64_t md_grey_300 __attribute__((swift_name("md_grey_300")));
@property (class, readonly) uint64_t md_grey_400 __attribute__((swift_name("md_grey_400")));
@property (class, readonly) uint64_t md_grey_50 __attribute__((swift_name("md_grey_50")));
@property (class, readonly) uint64_t md_grey_500 __attribute__((swift_name("md_grey_500")));
@property (class, readonly) uint64_t md_grey_600 __attribute__((swift_name("md_grey_600")));
@property (class, readonly) uint64_t md_grey_700 __attribute__((swift_name("md_grey_700")));
@property (class, readonly) uint64_t md_grey_800 __attribute__((swift_name("md_grey_800")));
@property (class, readonly) uint64_t md_grey_900 __attribute__((swift_name("md_grey_900")));
@property (class, readonly) uint64_t md_indigo_100 __attribute__((swift_name("md_indigo_100")));
@property (class, readonly) uint64_t md_indigo_200 __attribute__((swift_name("md_indigo_200")));
@property (class, readonly) uint64_t md_indigo_300 __attribute__((swift_name("md_indigo_300")));
@property (class, readonly) uint64_t md_indigo_400 __attribute__((swift_name("md_indigo_400")));
@property (class, readonly) uint64_t md_indigo_50 __attribute__((swift_name("md_indigo_50")));
@property (class, readonly) uint64_t md_indigo_500 __attribute__((swift_name("md_indigo_500")));
@property (class, readonly) uint64_t md_indigo_600 __attribute__((swift_name("md_indigo_600")));
@property (class, readonly) uint64_t md_indigo_700 __attribute__((swift_name("md_indigo_700")));
@property (class, readonly) uint64_t md_indigo_800 __attribute__((swift_name("md_indigo_800")));
@property (class, readonly) uint64_t md_indigo_900 __attribute__((swift_name("md_indigo_900")));
@property (class, readonly) uint64_t md_indigo_A100 __attribute__((swift_name("md_indigo_A100")));
@property (class, readonly) uint64_t md_indigo_A200 __attribute__((swift_name("md_indigo_A200")));
@property (class, readonly) uint64_t md_indigo_A400 __attribute__((swift_name("md_indigo_A400")));
@property (class, readonly) uint64_t md_indigo_A700 __attribute__((swift_name("md_indigo_A700")));
@property (class, readonly) uint64_t md_light_blue_100 __attribute__((swift_name("md_light_blue_100")));
@property (class, readonly) uint64_t md_light_blue_200 __attribute__((swift_name("md_light_blue_200")));
@property (class, readonly) uint64_t md_light_blue_300 __attribute__((swift_name("md_light_blue_300")));
@property (class, readonly) uint64_t md_light_blue_400 __attribute__((swift_name("md_light_blue_400")));
@property (class, readonly) uint64_t md_light_blue_50 __attribute__((swift_name("md_light_blue_50")));
@property (class, readonly) uint64_t md_light_blue_500 __attribute__((swift_name("md_light_blue_500")));
@property (class, readonly) uint64_t md_light_blue_600 __attribute__((swift_name("md_light_blue_600")));
@property (class, readonly) uint64_t md_light_blue_700 __attribute__((swift_name("md_light_blue_700")));
@property (class, readonly) uint64_t md_light_blue_800 __attribute__((swift_name("md_light_blue_800")));
@property (class, readonly) uint64_t md_light_blue_900 __attribute__((swift_name("md_light_blue_900")));
@property (class, readonly) uint64_t md_light_blue_A100 __attribute__((swift_name("md_light_blue_A100")));
@property (class, readonly) uint64_t md_light_blue_A200 __attribute__((swift_name("md_light_blue_A200")));
@property (class, readonly) uint64_t md_light_blue_A400 __attribute__((swift_name("md_light_blue_A400")));
@property (class, readonly) uint64_t md_light_blue_A700 __attribute__((swift_name("md_light_blue_A700")));
@property (class, readonly) uint64_t md_light_green_100 __attribute__((swift_name("md_light_green_100")));
@property (class, readonly) uint64_t md_light_green_200 __attribute__((swift_name("md_light_green_200")));
@property (class, readonly) uint64_t md_light_green_300 __attribute__((swift_name("md_light_green_300")));
@property (class, readonly) uint64_t md_light_green_400 __attribute__((swift_name("md_light_green_400")));
@property (class, readonly) uint64_t md_light_green_50 __attribute__((swift_name("md_light_green_50")));
@property (class, readonly) uint64_t md_light_green_500 __attribute__((swift_name("md_light_green_500")));
@property (class, readonly) uint64_t md_light_green_600 __attribute__((swift_name("md_light_green_600")));
@property (class, readonly) uint64_t md_light_green_700 __attribute__((swift_name("md_light_green_700")));
@property (class, readonly) uint64_t md_light_green_800 __attribute__((swift_name("md_light_green_800")));
@property (class, readonly) uint64_t md_light_green_900 __attribute__((swift_name("md_light_green_900")));
@property (class, readonly) uint64_t md_light_green_A100 __attribute__((swift_name("md_light_green_A100")));
@property (class, readonly) uint64_t md_light_green_A200 __attribute__((swift_name("md_light_green_A200")));
@property (class, readonly) uint64_t md_light_green_A400 __attribute__((swift_name("md_light_green_A400")));
@property (class, readonly) uint64_t md_light_green_A700 __attribute__((swift_name("md_light_green_A700")));
@property (class, readonly) uint64_t md_lime_100 __attribute__((swift_name("md_lime_100")));
@property (class, readonly) uint64_t md_lime_200 __attribute__((swift_name("md_lime_200")));
@property (class, readonly) uint64_t md_lime_300 __attribute__((swift_name("md_lime_300")));
@property (class, readonly) uint64_t md_lime_400 __attribute__((swift_name("md_lime_400")));
@property (class, readonly) uint64_t md_lime_50 __attribute__((swift_name("md_lime_50")));
@property (class, readonly) uint64_t md_lime_500 __attribute__((swift_name("md_lime_500")));
@property (class, readonly) uint64_t md_lime_600 __attribute__((swift_name("md_lime_600")));
@property (class, readonly) uint64_t md_lime_700 __attribute__((swift_name("md_lime_700")));
@property (class, readonly) uint64_t md_lime_800 __attribute__((swift_name("md_lime_800")));
@property (class, readonly) uint64_t md_lime_900 __attribute__((swift_name("md_lime_900")));
@property (class, readonly) uint64_t md_lime_A100 __attribute__((swift_name("md_lime_A100")));
@property (class, readonly) uint64_t md_lime_A200 __attribute__((swift_name("md_lime_A200")));
@property (class, readonly) uint64_t md_lime_A400 __attribute__((swift_name("md_lime_A400")));
@property (class, readonly) uint64_t md_lime_A700 __attribute__((swift_name("md_lime_A700")));
@property (class, readonly) uint64_t md_orange_100 __attribute__((swift_name("md_orange_100")));
@property (class, readonly) uint64_t md_orange_200 __attribute__((swift_name("md_orange_200")));
@property (class, readonly) uint64_t md_orange_300 __attribute__((swift_name("md_orange_300")));
@property (class, readonly) uint64_t md_orange_400 __attribute__((swift_name("md_orange_400")));
@property (class, readonly) uint64_t md_orange_50 __attribute__((swift_name("md_orange_50")));
@property (class, readonly) uint64_t md_orange_500 __attribute__((swift_name("md_orange_500")));
@property (class, readonly) uint64_t md_orange_600 __attribute__((swift_name("md_orange_600")));
@property (class, readonly) uint64_t md_orange_700 __attribute__((swift_name("md_orange_700")));
@property (class, readonly) uint64_t md_orange_800 __attribute__((swift_name("md_orange_800")));
@property (class, readonly) uint64_t md_orange_900 __attribute__((swift_name("md_orange_900")));
@property (class, readonly) uint64_t md_orange_A100 __attribute__((swift_name("md_orange_A100")));
@property (class, readonly) uint64_t md_orange_A200 __attribute__((swift_name("md_orange_A200")));
@property (class, readonly) uint64_t md_orange_A400 __attribute__((swift_name("md_orange_A400")));
@property (class, readonly) uint64_t md_orange_A700 __attribute__((swift_name("md_orange_A700")));
@property (class, readonly) uint64_t md_pink_100 __attribute__((swift_name("md_pink_100")));
@property (class, readonly) uint64_t md_pink_200 __attribute__((swift_name("md_pink_200")));
@property (class, readonly) uint64_t md_pink_300 __attribute__((swift_name("md_pink_300")));
@property (class, readonly) uint64_t md_pink_400 __attribute__((swift_name("md_pink_400")));
@property (class, readonly) uint64_t md_pink_50 __attribute__((swift_name("md_pink_50")));
@property (class, readonly) uint64_t md_pink_500 __attribute__((swift_name("md_pink_500")));
@property (class, readonly) uint64_t md_pink_600 __attribute__((swift_name("md_pink_600")));
@property (class, readonly) uint64_t md_pink_700 __attribute__((swift_name("md_pink_700")));
@property (class, readonly) uint64_t md_pink_800 __attribute__((swift_name("md_pink_800")));
@property (class, readonly) uint64_t md_pink_900 __attribute__((swift_name("md_pink_900")));
@property (class, readonly) uint64_t md_pink_A100 __attribute__((swift_name("md_pink_A100")));
@property (class, readonly) uint64_t md_pink_A200 __attribute__((swift_name("md_pink_A200")));
@property (class, readonly) uint64_t md_pink_A400 __attribute__((swift_name("md_pink_A400")));
@property (class, readonly) uint64_t md_pink_A700 __attribute__((swift_name("md_pink_A700")));
@property (class, readonly) uint64_t md_purple_100 __attribute__((swift_name("md_purple_100")));
@property (class, readonly) uint64_t md_purple_200 __attribute__((swift_name("md_purple_200")));
@property (class, readonly) uint64_t md_purple_300 __attribute__((swift_name("md_purple_300")));
@property (class, readonly) uint64_t md_purple_400 __attribute__((swift_name("md_purple_400")));
@property (class, readonly) uint64_t md_purple_50 __attribute__((swift_name("md_purple_50")));
@property (class, readonly) uint64_t md_purple_500 __attribute__((swift_name("md_purple_500")));
@property (class, readonly) uint64_t md_purple_600 __attribute__((swift_name("md_purple_600")));
@property (class, readonly) uint64_t md_purple_700 __attribute__((swift_name("md_purple_700")));
@property (class, readonly) uint64_t md_purple_800 __attribute__((swift_name("md_purple_800")));
@property (class, readonly) uint64_t md_purple_900 __attribute__((swift_name("md_purple_900")));
@property (class, readonly) uint64_t md_purple_A100 __attribute__((swift_name("md_purple_A100")));
@property (class, readonly) uint64_t md_purple_A200 __attribute__((swift_name("md_purple_A200")));
@property (class, readonly) uint64_t md_purple_A400 __attribute__((swift_name("md_purple_A400")));
@property (class, readonly) uint64_t md_purple_A700 __attribute__((swift_name("md_purple_A700")));
@property (class, readonly) uint64_t md_red_100 __attribute__((swift_name("md_red_100")));
@property (class, readonly) uint64_t md_red_200 __attribute__((swift_name("md_red_200")));
@property (class, readonly) uint64_t md_red_25 __attribute__((swift_name("md_red_25")));
@property (class, readonly) uint64_t md_red_300 __attribute__((swift_name("md_red_300")));
@property (class, readonly) uint64_t md_red_400 __attribute__((swift_name("md_red_400")));
@property (class, readonly) uint64_t md_red_50 __attribute__((swift_name("md_red_50")));
@property (class, readonly) uint64_t md_red_500 __attribute__((swift_name("md_red_500")));
@property (class, readonly) uint64_t md_red_600 __attribute__((swift_name("md_red_600")));
@property (class, readonly) uint64_t md_red_700 __attribute__((swift_name("md_red_700")));
@property (class, readonly) uint64_t md_red_800 __attribute__((swift_name("md_red_800")));
@property (class, readonly) uint64_t md_red_900 __attribute__((swift_name("md_red_900")));
@property (class, readonly) uint64_t md_red_A100 __attribute__((swift_name("md_red_A100")));
@property (class, readonly) uint64_t md_red_A200 __attribute__((swift_name("md_red_A200")));
@property (class, readonly) uint64_t md_red_A400 __attribute__((swift_name("md_red_A400")));
@property (class, readonly) uint64_t md_red_A700 __attribute__((swift_name("md_red_A700")));
@property (class, readonly) uint64_t md_teal_100 __attribute__((swift_name("md_teal_100")));
@property (class, readonly) uint64_t md_teal_200 __attribute__((swift_name("md_teal_200")));
@property (class, readonly) uint64_t md_teal_300 __attribute__((swift_name("md_teal_300")));
@property (class, readonly) uint64_t md_teal_400 __attribute__((swift_name("md_teal_400")));
@property (class, readonly) uint64_t md_teal_50 __attribute__((swift_name("md_teal_50")));
@property (class, readonly) uint64_t md_teal_500 __attribute__((swift_name("md_teal_500")));
@property (class, readonly) uint64_t md_teal_600 __attribute__((swift_name("md_teal_600")));
@property (class, readonly) uint64_t md_teal_700 __attribute__((swift_name("md_teal_700")));
@property (class, readonly) uint64_t md_teal_800 __attribute__((swift_name("md_teal_800")));
@property (class, readonly) uint64_t md_teal_900 __attribute__((swift_name("md_teal_900")));
@property (class, readonly) uint64_t md_teal_A100 __attribute__((swift_name("md_teal_A100")));
@property (class, readonly) uint64_t md_teal_A200 __attribute__((swift_name("md_teal_A200")));
@property (class, readonly) uint64_t md_teal_A400 __attribute__((swift_name("md_teal_A400")));
@property (class, readonly) uint64_t md_teal_A700 __attribute__((swift_name("md_teal_A700")));
@property (class, readonly) uint64_t md_transparent __attribute__((swift_name("md_transparent")));
@property (class, readonly) uint64_t md_white_1000 __attribute__((swift_name("md_white_1000")));
@property (class, readonly) uint64_t md_yellow_100 __attribute__((swift_name("md_yellow_100")));
@property (class, readonly) uint64_t md_yellow_200 __attribute__((swift_name("md_yellow_200")));
@property (class, readonly) uint64_t md_yellow_300 __attribute__((swift_name("md_yellow_300")));
@property (class, readonly) uint64_t md_yellow_400 __attribute__((swift_name("md_yellow_400")));
@property (class, readonly) uint64_t md_yellow_50 __attribute__((swift_name("md_yellow_50")));
@property (class, readonly) uint64_t md_yellow_500 __attribute__((swift_name("md_yellow_500")));
@property (class, readonly) uint64_t md_yellow_600 __attribute__((swift_name("md_yellow_600")));
@property (class, readonly) uint64_t md_yellow_700 __attribute__((swift_name("md_yellow_700")));
@property (class, readonly) uint64_t md_yellow_800 __attribute__((swift_name("md_yellow_800")));
@property (class, readonly) uint64_t md_yellow_900 __attribute__((swift_name("md_yellow_900")));
@property (class, readonly) uint64_t md_yellow_A100 __attribute__((swift_name("md_yellow_A100")));
@property (class, readonly) uint64_t md_yellow_A200 __attribute__((swift_name("md_yellow_A200")));
@property (class, readonly) uint64_t md_yellow_A400 __attribute__((swift_name("md_yellow_A400")));
@property (class, readonly) uint64_t md_yellow_A700 __attribute__((swift_name("md_yellow_A700")));
@property (class, readonly) uint64_t onBackgroundDark __attribute__((swift_name("onBackgroundDark")));
@property (class, readonly) uint64_t onBackgroundDarkHighContrast __attribute__((swift_name("onBackgroundDarkHighContrast")));
@property (class, readonly) uint64_t onBackgroundDarkMediumContrast __attribute__((swift_name("onBackgroundDarkMediumContrast")));
@property (class, readonly) uint64_t onBackgroundLight __attribute__((swift_name("onBackgroundLight")));
@property (class, readonly) uint64_t onBackgroundLightHighContrast __attribute__((swift_name("onBackgroundLightHighContrast")));
@property (class, readonly) uint64_t onBackgroundLightMediumContrast __attribute__((swift_name("onBackgroundLightMediumContrast")));
@property (class, readonly) uint64_t onErrorContainerDark __attribute__((swift_name("onErrorContainerDark")));
@property (class, readonly) uint64_t onErrorContainerDarkHighContrast __attribute__((swift_name("onErrorContainerDarkHighContrast")));
@property (class, readonly) uint64_t onErrorContainerDarkMediumContrast __attribute__((swift_name("onErrorContainerDarkMediumContrast")));
@property (class, readonly) uint64_t onErrorContainerLight __attribute__((swift_name("onErrorContainerLight")));
@property (class, readonly) uint64_t onErrorContainerLightHighContrast __attribute__((swift_name("onErrorContainerLightHighContrast")));
@property (class, readonly) uint64_t onErrorContainerLightMediumContrast __attribute__((swift_name("onErrorContainerLightMediumContrast")));
@property (class, readonly) uint64_t onErrorDark __attribute__((swift_name("onErrorDark")));
@property (class, readonly) uint64_t onErrorDarkHighContrast __attribute__((swift_name("onErrorDarkHighContrast")));
@property (class, readonly) uint64_t onErrorDarkMediumContrast __attribute__((swift_name("onErrorDarkMediumContrast")));
@property (class, readonly) uint64_t onErrorLight __attribute__((swift_name("onErrorLight")));
@property (class, readonly) uint64_t onErrorLightHighContrast __attribute__((swift_name("onErrorLightHighContrast")));
@property (class, readonly) uint64_t onErrorLightMediumContrast __attribute__((swift_name("onErrorLightMediumContrast")));
@property (class, readonly) uint64_t onPrimaryContainerDark __attribute__((swift_name("onPrimaryContainerDark")));
@property (class, readonly) uint64_t onPrimaryContainerDarkHighContrast __attribute__((swift_name("onPrimaryContainerDarkHighContrast")));
@property (class, readonly) uint64_t onPrimaryContainerDarkMediumContrast __attribute__((swift_name("onPrimaryContainerDarkMediumContrast")));
@property (class, readonly) uint64_t onPrimaryContainerLight __attribute__((swift_name("onPrimaryContainerLight")));
@property (class, readonly) uint64_t onPrimaryContainerLightHighContrast __attribute__((swift_name("onPrimaryContainerLightHighContrast")));
@property (class, readonly) uint64_t onPrimaryContainerLightMediumContrast __attribute__((swift_name("onPrimaryContainerLightMediumContrast")));
@property (class, readonly) uint64_t onPrimaryDark __attribute__((swift_name("onPrimaryDark")));
@property (class, readonly) uint64_t onPrimaryDarkHighContrast __attribute__((swift_name("onPrimaryDarkHighContrast")));
@property (class, readonly) uint64_t onPrimaryDarkMediumContrast __attribute__((swift_name("onPrimaryDarkMediumContrast")));
@property (class, readonly) uint64_t onPrimaryLight __attribute__((swift_name("onPrimaryLight")));
@property (class, readonly) uint64_t onPrimaryLightHighContrast __attribute__((swift_name("onPrimaryLightHighContrast")));
@property (class, readonly) uint64_t onPrimaryLightMediumContrast __attribute__((swift_name("onPrimaryLightMediumContrast")));
@property (class, readonly) uint64_t onSecondaryContainerDark __attribute__((swift_name("onSecondaryContainerDark")));
@property (class, readonly) uint64_t onSecondaryContainerDarkHighContrast __attribute__((swift_name("onSecondaryContainerDarkHighContrast")));
@property (class, readonly) uint64_t onSecondaryContainerDarkMediumContrast __attribute__((swift_name("onSecondaryContainerDarkMediumContrast")));
@property (class, readonly) uint64_t onSecondaryContainerLight __attribute__((swift_name("onSecondaryContainerLight")));
@property (class, readonly) uint64_t onSecondaryContainerLightHighContrast __attribute__((swift_name("onSecondaryContainerLightHighContrast")));
@property (class, readonly) uint64_t onSecondaryContainerLightMediumContrast __attribute__((swift_name("onSecondaryContainerLightMediumContrast")));
@property (class, readonly) uint64_t onSecondaryDark __attribute__((swift_name("onSecondaryDark")));
@property (class, readonly) uint64_t onSecondaryDarkHighContrast __attribute__((swift_name("onSecondaryDarkHighContrast")));
@property (class, readonly) uint64_t onSecondaryDarkMediumContrast __attribute__((swift_name("onSecondaryDarkMediumContrast")));
@property (class, readonly) uint64_t onSecondaryLight __attribute__((swift_name("onSecondaryLight")));
@property (class, readonly) uint64_t onSecondaryLightHighContrast __attribute__((swift_name("onSecondaryLightHighContrast")));
@property (class, readonly) uint64_t onSecondaryLightMediumContrast __attribute__((swift_name("onSecondaryLightMediumContrast")));
@property (class, readonly) uint64_t onSurfaceDark __attribute__((swift_name("onSurfaceDark")));
@property (class, readonly) uint64_t onSurfaceDarkHighContrast __attribute__((swift_name("onSurfaceDarkHighContrast")));
@property (class, readonly) uint64_t onSurfaceDarkMediumContrast __attribute__((swift_name("onSurfaceDarkMediumContrast")));
@property (class, readonly) uint64_t onSurfaceLight __attribute__((swift_name("onSurfaceLight")));
@property (class, readonly) uint64_t onSurfaceLightHighContrast __attribute__((swift_name("onSurfaceLightHighContrast")));
@property (class, readonly) uint64_t onSurfaceLightMediumContrast __attribute__((swift_name("onSurfaceLightMediumContrast")));
@property (class, readonly) uint64_t onSurfaceVariantDark __attribute__((swift_name("onSurfaceVariantDark")));
@property (class, readonly) uint64_t onSurfaceVariantDarkHighContrast __attribute__((swift_name("onSurfaceVariantDarkHighContrast")));
@property (class, readonly) uint64_t onSurfaceVariantDarkMediumContrast __attribute__((swift_name("onSurfaceVariantDarkMediumContrast")));
@property (class, readonly) uint64_t onSurfaceVariantLight __attribute__((swift_name("onSurfaceVariantLight")));
@property (class, readonly) uint64_t onSurfaceVariantLightHighContrast __attribute__((swift_name("onSurfaceVariantLightHighContrast")));
@property (class, readonly) uint64_t onSurfaceVariantLightMediumContrast __attribute__((swift_name("onSurfaceVariantLightMediumContrast")));
@property (class, readonly) uint64_t onTertiaryContainerDark __attribute__((swift_name("onTertiaryContainerDark")));
@property (class, readonly) uint64_t onTertiaryContainerDarkHighContrast __attribute__((swift_name("onTertiaryContainerDarkHighContrast")));
@property (class, readonly) uint64_t onTertiaryContainerDarkMediumContrast __attribute__((swift_name("onTertiaryContainerDarkMediumContrast")));
@property (class, readonly) uint64_t onTertiaryContainerLight __attribute__((swift_name("onTertiaryContainerLight")));
@property (class, readonly) uint64_t onTertiaryContainerLightHighContrast __attribute__((swift_name("onTertiaryContainerLightHighContrast")));
@property (class, readonly) uint64_t onTertiaryContainerLightMediumContrast __attribute__((swift_name("onTertiaryContainerLightMediumContrast")));
@property (class, readonly) uint64_t onTertiaryDark __attribute__((swift_name("onTertiaryDark")));
@property (class, readonly) uint64_t onTertiaryDarkHighContrast __attribute__((swift_name("onTertiaryDarkHighContrast")));
@property (class, readonly) uint64_t onTertiaryDarkMediumContrast __attribute__((swift_name("onTertiaryDarkMediumContrast")));
@property (class, readonly) uint64_t onTertiaryLight __attribute__((swift_name("onTertiaryLight")));
@property (class, readonly) uint64_t onTertiaryLightHighContrast __attribute__((swift_name("onTertiaryLightHighContrast")));
@property (class, readonly) uint64_t onTertiaryLightMediumContrast __attribute__((swift_name("onTertiaryLightMediumContrast")));
@property (class, readonly) uint64_t outlineDark __attribute__((swift_name("outlineDark")));
@property (class, readonly) uint64_t outlineDarkHighContrast __attribute__((swift_name("outlineDarkHighContrast")));
@property (class, readonly) uint64_t outlineDarkMediumContrast __attribute__((swift_name("outlineDarkMediumContrast")));
@property (class, readonly) uint64_t outlineLight __attribute__((swift_name("outlineLight")));
@property (class, readonly) uint64_t outlineLightHighContrast __attribute__((swift_name("outlineLightHighContrast")));
@property (class, readonly) uint64_t outlineLightMediumContrast __attribute__((swift_name("outlineLightMediumContrast")));
@property (class, readonly) uint64_t outlineVariantDark __attribute__((swift_name("outlineVariantDark")));
@property (class, readonly) uint64_t outlineVariantDarkHighContrast __attribute__((swift_name("outlineVariantDarkHighContrast")));
@property (class, readonly) uint64_t outlineVariantDarkMediumContrast __attribute__((swift_name("outlineVariantDarkMediumContrast")));
@property (class, readonly) uint64_t outlineVariantLight __attribute__((swift_name("outlineVariantLight")));
@property (class, readonly) uint64_t outlineVariantLightHighContrast __attribute__((swift_name("outlineVariantLightHighContrast")));
@property (class, readonly) uint64_t outlineVariantLightMediumContrast __attribute__((swift_name("outlineVariantLightMediumContrast")));
@property (class, readonly) uint64_t primaryContainerDark __attribute__((swift_name("primaryContainerDark")));
@property (class, readonly) uint64_t primaryContainerDarkHighContrast __attribute__((swift_name("primaryContainerDarkHighContrast")));
@property (class, readonly) uint64_t primaryContainerDarkMediumContrast __attribute__((swift_name("primaryContainerDarkMediumContrast")));
@property (class, readonly) uint64_t primaryContainerLight __attribute__((swift_name("primaryContainerLight")));
@property (class, readonly) uint64_t primaryContainerLightHighContrast __attribute__((swift_name("primaryContainerLightHighContrast")));
@property (class, readonly) uint64_t primaryContainerLightMediumContrast __attribute__((swift_name("primaryContainerLightMediumContrast")));
@property (class, readonly) uint64_t primaryDark __attribute__((swift_name("primaryDark")));
@property (class, readonly) uint64_t primaryDarkHighContrast __attribute__((swift_name("primaryDarkHighContrast")));
@property (class, readonly) uint64_t primaryDarkMediumContrast __attribute__((swift_name("primaryDarkMediumContrast")));
@property (class, readonly) uint64_t primaryLight __attribute__((swift_name("primaryLight")));
@property (class, readonly) uint64_t primaryLightHighContrast __attribute__((swift_name("primaryLightHighContrast")));
@property (class, readonly) uint64_t primaryLightMediumContrast __attribute__((swift_name("primaryLightMediumContrast")));
@property (class, readonly) uint64_t scrimDark __attribute__((swift_name("scrimDark")));
@property (class, readonly) uint64_t scrimDarkHighContrast __attribute__((swift_name("scrimDarkHighContrast")));
@property (class, readonly) uint64_t scrimDarkMediumContrast __attribute__((swift_name("scrimDarkMediumContrast")));
@property (class, readonly) uint64_t scrimLight __attribute__((swift_name("scrimLight")));
@property (class, readonly) uint64_t scrimLightHighContrast __attribute__((swift_name("scrimLightHighContrast")));
@property (class, readonly) uint64_t scrimLightMediumContrast __attribute__((swift_name("scrimLightMediumContrast")));
@property (class, readonly) uint64_t secondaryContainerDark __attribute__((swift_name("secondaryContainerDark")));
@property (class, readonly) uint64_t secondaryContainerDarkHighContrast __attribute__((swift_name("secondaryContainerDarkHighContrast")));
@property (class, readonly) uint64_t secondaryContainerDarkMediumContrast __attribute__((swift_name("secondaryContainerDarkMediumContrast")));
@property (class, readonly) uint64_t secondaryContainerLight __attribute__((swift_name("secondaryContainerLight")));
@property (class, readonly) uint64_t secondaryContainerLightHighContrast __attribute__((swift_name("secondaryContainerLightHighContrast")));
@property (class, readonly) uint64_t secondaryContainerLightMediumContrast __attribute__((swift_name("secondaryContainerLightMediumContrast")));
@property (class, readonly) uint64_t secondaryDark __attribute__((swift_name("secondaryDark")));
@property (class, readonly) uint64_t secondaryDarkHighContrast __attribute__((swift_name("secondaryDarkHighContrast")));
@property (class, readonly) uint64_t secondaryDarkMediumContrast __attribute__((swift_name("secondaryDarkMediumContrast")));
@property (class, readonly) uint64_t secondaryLight __attribute__((swift_name("secondaryLight")));
@property (class, readonly) uint64_t secondaryLightHighContrast __attribute__((swift_name("secondaryLightHighContrast")));
@property (class, readonly) uint64_t secondaryLightMediumContrast __attribute__((swift_name("secondaryLightMediumContrast")));
@property (class, readonly) uint64_t surfaceBrightDark __attribute__((swift_name("surfaceBrightDark")));
@property (class, readonly) uint64_t surfaceBrightDarkHighContrast __attribute__((swift_name("surfaceBrightDarkHighContrast")));
@property (class, readonly) uint64_t surfaceBrightDarkMediumContrast __attribute__((swift_name("surfaceBrightDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceBrightLight __attribute__((swift_name("surfaceBrightLight")));
@property (class, readonly) uint64_t surfaceBrightLightHighContrast __attribute__((swift_name("surfaceBrightLightHighContrast")));
@property (class, readonly) uint64_t surfaceBrightLightMediumContrast __attribute__((swift_name("surfaceBrightLightMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerDark __attribute__((swift_name("surfaceContainerDark")));
@property (class, readonly) uint64_t surfaceContainerDarkHighContrast __attribute__((swift_name("surfaceContainerDarkHighContrast")));
@property (class, readonly) uint64_t surfaceContainerDarkMediumContrast __attribute__((swift_name("surfaceContainerDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerHighDark __attribute__((swift_name("surfaceContainerHighDark")));
@property (class, readonly) uint64_t surfaceContainerHighDarkHighContrast __attribute__((swift_name("surfaceContainerHighDarkHighContrast")));
@property (class, readonly) uint64_t surfaceContainerHighDarkMediumContrast __attribute__((swift_name("surfaceContainerHighDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerHighLight __attribute__((swift_name("surfaceContainerHighLight")));
@property (class, readonly) uint64_t surfaceContainerHighLightHighContrast __attribute__((swift_name("surfaceContainerHighLightHighContrast")));
@property (class, readonly) uint64_t surfaceContainerHighLightMediumContrast __attribute__((swift_name("surfaceContainerHighLightMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerHighestDark __attribute__((swift_name("surfaceContainerHighestDark")));
@property (class, readonly) uint64_t surfaceContainerHighestDarkHighContrast __attribute__((swift_name("surfaceContainerHighestDarkHighContrast")));
@property (class, readonly) uint64_t surfaceContainerHighestDarkMediumContrast __attribute__((swift_name("surfaceContainerHighestDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerHighestLight __attribute__((swift_name("surfaceContainerHighestLight")));
@property (class, readonly) uint64_t surfaceContainerHighestLightHighContrast __attribute__((swift_name("surfaceContainerHighestLightHighContrast")));
@property (class, readonly) uint64_t surfaceContainerHighestLightMediumContrast __attribute__((swift_name("surfaceContainerHighestLightMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerLight __attribute__((swift_name("surfaceContainerLight")));
@property (class, readonly) uint64_t surfaceContainerLightHighContrast __attribute__((swift_name("surfaceContainerLightHighContrast")));
@property (class, readonly) uint64_t surfaceContainerLightMediumContrast __attribute__((swift_name("surfaceContainerLightMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerLowDark __attribute__((swift_name("surfaceContainerLowDark")));
@property (class, readonly) uint64_t surfaceContainerLowDarkHighContrast __attribute__((swift_name("surfaceContainerLowDarkHighContrast")));
@property (class, readonly) uint64_t surfaceContainerLowDarkMediumContrast __attribute__((swift_name("surfaceContainerLowDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerLowLight __attribute__((swift_name("surfaceContainerLowLight")));
@property (class, readonly) uint64_t surfaceContainerLowLightHighContrast __attribute__((swift_name("surfaceContainerLowLightHighContrast")));
@property (class, readonly) uint64_t surfaceContainerLowLightMediumContrast __attribute__((swift_name("surfaceContainerLowLightMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerLowestDark __attribute__((swift_name("surfaceContainerLowestDark")));
@property (class, readonly) uint64_t surfaceContainerLowestDarkHighContrast __attribute__((swift_name("surfaceContainerLowestDarkHighContrast")));
@property (class, readonly) uint64_t surfaceContainerLowestDarkMediumContrast __attribute__((swift_name("surfaceContainerLowestDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceContainerLowestLight __attribute__((swift_name("surfaceContainerLowestLight")));
@property (class, readonly) uint64_t surfaceContainerLowestLightHighContrast __attribute__((swift_name("surfaceContainerLowestLightHighContrast")));
@property (class, readonly) uint64_t surfaceContainerLowestLightMediumContrast __attribute__((swift_name("surfaceContainerLowestLightMediumContrast")));
@property (class, readonly) uint64_t surfaceDark __attribute__((swift_name("surfaceDark")));
@property (class, readonly) uint64_t surfaceDarkHighContrast __attribute__((swift_name("surfaceDarkHighContrast")));
@property (class, readonly) uint64_t surfaceDarkMediumContrast __attribute__((swift_name("surfaceDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceDimDark __attribute__((swift_name("surfaceDimDark")));
@property (class, readonly) uint64_t surfaceDimDarkHighContrast __attribute__((swift_name("surfaceDimDarkHighContrast")));
@property (class, readonly) uint64_t surfaceDimDarkMediumContrast __attribute__((swift_name("surfaceDimDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceDimLight __attribute__((swift_name("surfaceDimLight")));
@property (class, readonly) uint64_t surfaceDimLightHighContrast __attribute__((swift_name("surfaceDimLightHighContrast")));
@property (class, readonly) uint64_t surfaceDimLightMediumContrast __attribute__((swift_name("surfaceDimLightMediumContrast")));
@property (class, readonly) uint64_t surfaceLight __attribute__((swift_name("surfaceLight")));
@property (class, readonly) uint64_t surfaceLightHighContrast __attribute__((swift_name("surfaceLightHighContrast")));
@property (class, readonly) uint64_t surfaceLightMediumContrast __attribute__((swift_name("surfaceLightMediumContrast")));
@property (class, readonly) uint64_t surfaceVariantDark __attribute__((swift_name("surfaceVariantDark")));
@property (class, readonly) uint64_t surfaceVariantDarkHighContrast __attribute__((swift_name("surfaceVariantDarkHighContrast")));
@property (class, readonly) uint64_t surfaceVariantDarkMediumContrast __attribute__((swift_name("surfaceVariantDarkMediumContrast")));
@property (class, readonly) uint64_t surfaceVariantLight __attribute__((swift_name("surfaceVariantLight")));
@property (class, readonly) uint64_t surfaceVariantLightHighContrast __attribute__((swift_name("surfaceVariantLightHighContrast")));
@property (class, readonly) uint64_t surfaceVariantLightMediumContrast __attribute__((swift_name("surfaceVariantLightMediumContrast")));
@property (class, readonly) uint64_t tertiaryContainerDark __attribute__((swift_name("tertiaryContainerDark")));
@property (class, readonly) uint64_t tertiaryContainerDarkHighContrast __attribute__((swift_name("tertiaryContainerDarkHighContrast")));
@property (class, readonly) uint64_t tertiaryContainerDarkMediumContrast __attribute__((swift_name("tertiaryContainerDarkMediumContrast")));
@property (class, readonly) uint64_t tertiaryContainerLight __attribute__((swift_name("tertiaryContainerLight")));
@property (class, readonly) uint64_t tertiaryContainerLightHighContrast __attribute__((swift_name("tertiaryContainerLightHighContrast")));
@property (class, readonly) uint64_t tertiaryContainerLightMediumContrast __attribute__((swift_name("tertiaryContainerLightMediumContrast")));
@property (class, readonly) uint64_t tertiaryDark __attribute__((swift_name("tertiaryDark")));
@property (class, readonly) uint64_t tertiaryDarkHighContrast __attribute__((swift_name("tertiaryDarkHighContrast")));
@property (class, readonly) uint64_t tertiaryDarkMediumContrast __attribute__((swift_name("tertiaryDarkMediumContrast")));
@property (class, readonly) uint64_t tertiaryLight __attribute__((swift_name("tertiaryLight")));
@property (class, readonly) uint64_t tertiaryLightHighContrast __attribute__((swift_name("tertiaryLightHighContrast")));
@property (class, readonly) uint64_t tertiaryLightMediumContrast __attribute__((swift_name("tertiaryLightMediumContrast")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KoinInitializerKt")))
@interface SharedKoinInitializerKt : SharedBase
+ (void)doInitKoin __attribute__((swift_name("doInitKoin()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformModule_iosKt")))
@interface SharedPlatformModule_iosKt : SharedBase

/**
 * iOS-specific Koin module
 */
@property (class, readonly) SharedKoin_coreModule *platformModule __attribute__((swift_name("platformModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SharedModuleKt")))
@interface SharedSharedModuleKt : SharedBase

/**
 * Koin DI module for shared KMP code
 */
@property (class, readonly) SharedKoin_coreModule *sharedModule __attribute__((swift_name("sharedModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ThemeKt")))
@interface SharedThemeKt : SharedBase
@property (class, readonly) SharedColorFamily *unspecified_scheme __attribute__((swift_name("unspecified_scheme")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TravlogueApiClientKt")))
@interface SharedTravlogueApiClientKt : SharedBase

/**
 * Factory function to create HttpClient with common configuration
 */
+ (SharedKtor_client_coreHttpClient *)createHttpClientEnableLogging:(BOOL)enableLogging __attribute__((swift_name("createHttpClient(enableLogging:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TypeKt")))
@interface SharedTypeKt : SharedBase
@property (class, readonly) SharedMaterial3Typography *AppTypography __attribute__((swift_name("AppTypography")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UUIDKt")))
@interface SharedUUIDKt : SharedBase

/**
 * Generates a UUID string
 * KMP-compatible UUID generation
 */
+ (NSString *)generateUUID __attribute__((swift_name("generateUUID()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoin")))
@interface SharedKoin_coreKoin : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)close __attribute__((swift_name("close()")));
- (void)createEagerInstances __attribute__((swift_name("createEagerInstances()")));
- (SharedKoin_coreScope *)createScopeT:(id<SharedKoin_coreKoinScopeComponent>)t __attribute__((swift_name("createScope(t:)")));
- (SharedKoin_coreScope *)createScopeScopeId:(NSString *)scopeId __attribute__((swift_name("createScope(scopeId:)")));
- (SharedKoin_coreScope *)createScopeScopeId:(NSString *)scopeId source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:source:)")));
- (SharedKoin_coreScope *)createScopeScopeId:(NSString *)scopeId qualifier:(id<SharedKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:qualifier:source:)")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<SharedKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (void)deleteScopeScopeId:(NSString *)scopeId __attribute__((swift_name("deleteScope(scopeId:)")));
- (id)getQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (id _Nullable)getClazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (SharedKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getOrCreateScope(scopeId:)")));
- (SharedKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId qualifier:(id<SharedKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("getOrCreateScope(scopeId:qualifier:source:)")));
- (id _Nullable)getOrNullQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id _Nullable)getOrNullClazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (SharedKoin_coreScope *)getScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getScope(scopeId:)")));
- (SharedKoin_coreScope * _Nullable)getScopeOrNullScopeId:(NSString *)scopeId __attribute__((swift_name("getScopeOrNull(scopeId:)")));
- (id<SharedKotlinLazy>)injectQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier mode:(SharedKotlinLazyThreadSafetyMode *)mode parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<SharedKotlinLazy>)injectOrNullQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier mode:(SharedKotlinLazyThreadSafetyMode *)mode parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (void)loadModulesModules:(NSArray<SharedKoin_coreModule *> *)modules allowOverride:(BOOL)allowOverride createEagerInstances:(BOOL)createEagerInstances __attribute__((swift_name("loadModules(modules:allowOverride:createEagerInstances:)")));
- (void)setPropertyKey:(NSString *)key value:(id)value __attribute__((swift_name("setProperty(key:value:)")));
- (void)setupLoggerLogger:(SharedKoin_coreLogger *)logger __attribute__((swift_name("setupLogger(logger:)")));
- (void)unloadModulesModules:(NSArray<SharedKoin_coreModule *> *)modules __attribute__((swift_name("unloadModules(modules:)")));
@property (readonly) SharedKoin_coreExtensionManager *extensionManager __attribute__((swift_name("extensionManager")));
@property (readonly) SharedKoin_coreInstanceRegistry *instanceRegistry __attribute__((swift_name("instanceRegistry")));
@property (readonly) SharedKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) SharedKoin_corePropertyRegistry *propertyRegistry __attribute__((swift_name("propertyRegistry")));
@property (readonly) SharedKoin_coreScopeRegistry *scopeRegistry __attribute__((swift_name("scopeRegistry")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/datetime/serializers/InstantIso8601Serializer))
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeInstant")))
@interface SharedKotlinx_datetimeInstant : SharedBase <SharedKotlinComparable>
@property (class, readonly, getter=companion) SharedKotlinx_datetimeInstantCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedKotlinx_datetimeInstant *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedKotlinx_datetimeInstant *)minusDuration:(int64_t)duration __attribute__((swift_name("minus(duration:)")));
- (int64_t)minusOther:(SharedKotlinx_datetimeInstant *)other __attribute__((swift_name("minus(other:)")));
- (SharedKotlinx_datetimeInstant *)plusDuration:(int64_t)duration __attribute__((swift_name("plus(duration:)")));
- (int64_t)toEpochMilliseconds __attribute__((swift_name("toEpochMilliseconds()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t epochSeconds __attribute__((swift_name("epochSeconds")));
@property (readonly) int32_t nanosecondsOfSecond __attribute__((swift_name("nanosecondsOfSecond")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/datetime/serializers/LocalDateTimeIso8601Serializer))
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeLocalDateTime")))
@interface SharedKotlinx_datetimeLocalDateTime : SharedBase <SharedKotlinComparable>
- (instancetype)initWithDate:(SharedKotlinx_datetimeLocalDate *)date time:(SharedKotlinx_datetimeLocalTime *)time __attribute__((swift_name("init(date:time:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithYear:(int32_t)year monthNumber:(int32_t)monthNumber dayOfMonth:(int32_t)dayOfMonth hour:(int32_t)hour minute:(int32_t)minute second:(int32_t)second nanosecond:(int32_t)nanosecond __attribute__((swift_name("init(year:monthNumber:dayOfMonth:hour:minute:second:nanosecond:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithYear:(int32_t)year month:(SharedKotlinx_datetimeMonth *)month dayOfMonth:(int32_t)dayOfMonth hour:(int32_t)hour minute:(int32_t)minute second:(int32_t)second nanosecond:(int32_t)nanosecond __attribute__((swift_name("init(year:month:dayOfMonth:hour:minute:second:nanosecond:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinx_datetimeLocalDateTimeCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedKotlinx_datetimeLocalDateTime *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinx_datetimeLocalDate *date __attribute__((swift_name("date")));
@property (readonly) int32_t dayOfMonth __attribute__((swift_name("dayOfMonth")));
@property (readonly) SharedKotlinx_datetimeDayOfWeek *dayOfWeek __attribute__((swift_name("dayOfWeek")));
@property (readonly) int32_t dayOfYear __attribute__((swift_name("dayOfYear")));
@property (readonly) int32_t hour __attribute__((swift_name("hour")));
@property (readonly) int32_t minute __attribute__((swift_name("minute")));
@property (readonly) SharedKotlinx_datetimeMonth *month __attribute__((swift_name("month")));
@property (readonly) int32_t monthNumber __attribute__((swift_name("monthNumber")));
@property (readonly) int32_t nanosecond __attribute__((swift_name("nanosecond")));
@property (readonly) int32_t second __attribute__((swift_name("second")));
@property (readonly) SharedKotlinx_datetimeLocalTime *time __attribute__((swift_name("time")));
@property (readonly) int32_t year __attribute__((swift_name("year")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/datetime/serializers/LocalDateIso8601Serializer))
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeLocalDate")))
@interface SharedKotlinx_datetimeLocalDate : SharedBase <SharedKotlinComparable>
- (instancetype)initWithYear:(int32_t)year monthNumber:(int32_t)monthNumber dayOfMonth:(int32_t)dayOfMonth __attribute__((swift_name("init(year:monthNumber:dayOfMonth:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithYear:(int32_t)year month:(SharedKotlinx_datetimeMonth *)month dayOfMonth:(int32_t)dayOfMonth __attribute__((swift_name("init(year:month:dayOfMonth:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinx_datetimeLocalDateCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedKotlinx_datetimeLocalDate *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (int32_t)toEpochDays __attribute__((swift_name("toEpochDays()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t dayOfMonth __attribute__((swift_name("dayOfMonth")));
@property (readonly) SharedKotlinx_datetimeDayOfWeek *dayOfWeek __attribute__((swift_name("dayOfWeek")));
@property (readonly) int32_t dayOfYear __attribute__((swift_name("dayOfYear")));
@property (readonly) SharedKotlinx_datetimeMonth *month __attribute__((swift_name("month")));
@property (readonly) int32_t monthNumber __attribute__((swift_name("monthNumber")));
@property (readonly) int32_t year __attribute__((swift_name("year")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface SharedKotlinThrowable : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (SharedKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("RuntimeCloseable")))
@protocol SharedRuntimeCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end

__attribute__((swift_name("RuntimeSqlDriver")))
@protocol SharedRuntimeSqlDriver <SharedRuntimeCloseable>
@required
- (void)addListenerQueryKeys:(SharedKotlinArray<NSString *> *)queryKeys listener:(id<SharedRuntimeQueryListener>)listener __attribute__((swift_name("addListener(queryKeys:listener:)")));
- (SharedRuntimeTransacterTransaction * _Nullable)currentTransaction __attribute__((swift_name("currentTransaction()")));
- (id<SharedRuntimeQueryResult>)executeIdentifier:(SharedInt * _Nullable)identifier sql:(NSString *)sql parameters:(int32_t)parameters binders:(void (^ _Nullable)(id<SharedRuntimeSqlPreparedStatement>))binders __attribute__((swift_name("execute(identifier:sql:parameters:binders:)")));
- (id<SharedRuntimeQueryResult>)executeQueryIdentifier:(SharedInt * _Nullable)identifier sql:(NSString *)sql mapper:(id<SharedRuntimeQueryResult> (^)(id<SharedRuntimeSqlCursor>))mapper parameters:(int32_t)parameters binders:(void (^ _Nullable)(id<SharedRuntimeSqlPreparedStatement>))binders __attribute__((swift_name("executeQuery(identifier:sql:mapper:parameters:binders:)")));
- (id<SharedRuntimeQueryResult>)doNewTransaction __attribute__((swift_name("doNewTransaction()")));
- (void)notifyListenersQueryKeys:(SharedKotlinArray<NSString *> *)queryKeys __attribute__((swift_name("notifyListeners(queryKeys:)")));
- (void)removeListenerQueryKeys:(SharedKotlinArray<NSString *> *)queryKeys listener:(id<SharedRuntimeQueryListener>)listener __attribute__((swift_name("removeListener(queryKeys:listener:)")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineScope")))
@protocol SharedKotlinx_coroutines_coreCoroutineScope
@required
@property (readonly) id<SharedKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end

__attribute__((swift_name("Ktor_ioCloseable")))
@protocol SharedKtor_ioCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpClient")))
@interface SharedKtor_client_coreHttpClient : SharedBase <SharedKotlinx_coroutines_coreCoroutineScope, SharedKtor_ioCloseable>
- (instancetype)initWithEngine:(id<SharedKtor_client_coreHttpClientEngine>)engine userConfig:(SharedKtor_client_coreHttpClientConfig<SharedKtor_client_coreHttpClientEngineConfig *> *)userConfig __attribute__((swift_name("init(engine:userConfig:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (SharedKtor_client_coreHttpClient *)configBlock:(void (^)(SharedKtor_client_coreHttpClientConfig<id> *))block __attribute__((swift_name("config(block:)")));
- (BOOL)isSupportedCapability:(id<SharedKtor_client_coreHttpClientEngineCapability>)capability __attribute__((swift_name("isSupported(capability:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<SharedKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) id<SharedKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<SharedKtor_client_coreHttpClientEngine> engine __attribute__((swift_name("engine")));
@property (readonly) SharedKtor_client_coreHttpClientEngineConfig *engineConfig __attribute__((swift_name("engineConfig")));
@property (readonly) SharedKtor_eventsEvents *monitor __attribute__((swift_name("monitor")));
@property (readonly) SharedKtor_client_coreHttpReceivePipeline *receivePipeline __attribute__((swift_name("receivePipeline")));
@property (readonly) SharedKtor_client_coreHttpRequestPipeline *requestPipeline __attribute__((swift_name("requestPipeline")));
@property (readonly) SharedKtor_client_coreHttpResponsePipeline *responsePipeline __attribute__((swift_name("responsePipeline")));
@property (readonly) SharedKtor_client_coreHttpSendPipeline *sendPipeline __attribute__((swift_name("sendPipeline")));
@end

__attribute__((swift_name("KotlinException")))
@interface SharedKotlinException : SharedKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface SharedKotlinRuntimeException : SharedKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface SharedKotlinIllegalStateException : SharedKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface SharedKotlinCancellationException : SharedKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol SharedKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<SharedKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<SharedKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol SharedKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<SharedKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<SharedKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol SharedKotlinx_serialization_coreKSerializer <SharedKotlinx_serialization_coreSerializationStrategy, SharedKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol SharedKotlinx_coroutines_coreFlow
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<SharedKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface SharedKotlinEnumCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface SharedKotlinArray<T> : SharedBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(SharedInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<SharedKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface SharedKotlinUnit : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinUnit *shared __attribute__((swift_name("shared")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("RuntimeTransactionCallbacks")))
@protocol SharedRuntimeTransactionCallbacks
@required
- (void)afterCommitFunction:(void (^)(void))function __attribute__((swift_name("afterCommit(function:)")));
- (void)afterRollbackFunction:(void (^)(void))function __attribute__((swift_name("afterRollback(function:)")));
@end

__attribute__((swift_name("RuntimeTransacterTransaction")))
@interface SharedRuntimeTransacterTransaction : SharedBase <SharedRuntimeTransactionCallbacks>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)afterCommitFunction:(void (^)(void))function __attribute__((swift_name("afterCommit(function:)")));
- (void)afterRollbackFunction:(void (^)(void))function __attribute__((swift_name("afterRollback(function:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (id<SharedRuntimeQueryResult>)endTransactionSuccessful:(BOOL)successful __attribute__((swift_name("endTransaction(successful:)")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) SharedRuntimeTransacterTransaction * _Nullable enclosingTransaction __attribute__((swift_name("enclosingTransaction")));
@end

__attribute__((swift_name("RuntimeTransactionWithoutReturn")))
@protocol SharedRuntimeTransactionWithoutReturn <SharedRuntimeTransactionCallbacks>
@required
- (void)rollback __attribute__((swift_name("rollback()")));
- (void)transactionBody:(void (^)(id<SharedRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(body:)")));
@end

__attribute__((swift_name("RuntimeTransactionWithReturn")))
@protocol SharedRuntimeTransactionWithReturn <SharedRuntimeTransactionCallbacks>
@required
- (void)rollbackReturnValue:(id _Nullable)returnValue __attribute__((swift_name("rollback(returnValue:)")));
- (id _Nullable)transactionBody_:(id _Nullable (^)(id<SharedRuntimeTransactionWithReturn>))body __attribute__((swift_name("transaction(body_:)")));
@end

__attribute__((swift_name("RuntimeExecutableQuery")))
@interface SharedRuntimeExecutableQuery<__covariant RowType> : SharedBase
- (instancetype)initWithMapper:(RowType (^)(id<SharedRuntimeSqlCursor>))mapper __attribute__((swift_name("init(mapper:)"))) __attribute__((objc_designated_initializer));
- (id<SharedRuntimeQueryResult>)executeMapper:(id<SharedRuntimeQueryResult> (^)(id<SharedRuntimeSqlCursor>))mapper __attribute__((swift_name("execute(mapper:)")));
- (NSArray<RowType> *)executeAsList __attribute__((swift_name("executeAsList()")));
- (RowType)executeAsOne __attribute__((swift_name("executeAsOne()")));
- (RowType _Nullable)executeAsOneOrNull __attribute__((swift_name("executeAsOneOrNull()")));
@property (readonly) RowType (^mapper)(id<SharedRuntimeSqlCursor>) __attribute__((swift_name("mapper")));
@end

__attribute__((swift_name("RuntimeQuery")))
@interface SharedRuntimeQuery<__covariant RowType> : SharedRuntimeExecutableQuery<RowType>
- (instancetype)initWithMapper:(RowType (^)(id<SharedRuntimeSqlCursor>))mapper __attribute__((swift_name("init(mapper:)"))) __attribute__((objc_designated_initializer));
- (void)addListenerListener:(id<SharedRuntimeQueryListener>)listener __attribute__((swift_name("addListener(listener:)")));
- (void)removeListenerListener:(id<SharedRuntimeQueryListener>)listener __attribute__((swift_name("removeListener(listener:)")));
@end

__attribute__((swift_name("RuntimeSqlSchema")))
@protocol SharedRuntimeSqlSchema
@required
- (id<SharedRuntimeQueryResult>)createDriver:(id<SharedRuntimeSqlDriver>)driver __attribute__((swift_name("create(driver:)")));
- (id<SharedRuntimeQueryResult>)migrateDriver:(id<SharedRuntimeSqlDriver>)driver oldVersion:(int64_t)oldVersion newVersion:(int64_t)newVersion callbacks:(SharedKotlinArray<SharedRuntimeAfterVersion *> *)callbacks __attribute__((swift_name("migrate(driver:oldVersion:newVersion:callbacks:)")));
@property (readonly) int64_t version __attribute__((swift_name("version")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="2.0")
*/
__attribute__((swift_name("KotlinAutoCloseable")))
@protocol SharedKotlinAutoCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreSharedFlow")))
@protocol SharedKotlinx_coroutines_coreSharedFlow <SharedKotlinx_coroutines_coreFlow>
@required
@property (readonly) NSArray<id> *replayCache __attribute__((swift_name("replayCache")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreStateFlow")))
@protocol SharedKotlinx_coroutines_coreStateFlow <SharedKotlinx_coroutines_coreSharedFlow>
@required
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreModule")))
@interface SharedKoin_coreModule : SharedBase
- (instancetype)initWith_createdAtStart:(BOOL)_createdAtStart __attribute__((swift_name("init(_createdAtStart:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (SharedKoin_coreKoinDefinition<id> *)factoryQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(SharedKoin_coreScope *, SharedKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (void)includesModule:(SharedKotlinArray<SharedKoin_coreModule *> *)module __attribute__((swift_name("includes(module:)")));
- (void)includesModule_:(id)module __attribute__((swift_name("includes(module_:)")));
- (void)indexPrimaryTypeInstanceFactory:(SharedKoin_coreInstanceFactory<id> *)instanceFactory __attribute__((swift_name("indexPrimaryType(instanceFactory:)")));
- (void)indexSecondaryTypesInstanceFactory:(SharedKoin_coreInstanceFactory<id> *)instanceFactory __attribute__((swift_name("indexSecondaryTypes(instanceFactory:)")));
- (NSArray<SharedKoin_coreModule *> *)plusModules:(NSArray<SharedKoin_coreModule *> *)modules __attribute__((swift_name("plus(modules:)")));
- (NSArray<SharedKoin_coreModule *> *)plusModule:(SharedKoin_coreModule *)module __attribute__((swift_name("plus(module:)")));
- (void)prepareForCreationAtStartInstanceFactory:(SharedKoin_coreSingleInstanceFactory<id> *)instanceFactory __attribute__((swift_name("prepareForCreationAtStart(instanceFactory:)")));
- (void)scopeScopeSet:(void (^)(SharedKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(scopeSet:)")));
- (void)scopeQualifier:(id<SharedKoin_coreQualifier>)qualifier scopeSet:(void (^)(SharedKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(qualifier:scopeSet:)")));
- (SharedKoin_coreKoinDefinition<id> *)singleQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier createdAtStart:(BOOL)createdAtStart definition:(id _Nullable (^)(SharedKoin_coreScope *, SharedKoin_coreParametersHolder *))definition __attribute__((swift_name("single(qualifier:createdAtStart:definition:)")));
@property (readonly) SharedMutableSet<SharedKoin_coreSingleInstanceFactory<id> *> *eagerInstances __attribute__((swift_name("eagerInstances")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSMutableArray<SharedKoin_coreModule *> *includedModules __attribute__((swift_name("includedModules")));
@property (readonly) BOOL isLoaded __attribute__((swift_name("isLoaded")));
@property (readonly) SharedMutableDictionary<NSString *, SharedKoin_coreInstanceFactory<id> *> *mappings __attribute__((swift_name("mappings")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Material3Typography")))
@interface SharedMaterial3Typography : SharedBase
- (instancetype)initWithDisplayLarge:(SharedUi_textTextStyle *)displayLarge displayMedium:(SharedUi_textTextStyle *)displayMedium displaySmall:(SharedUi_textTextStyle *)displaySmall headlineLarge:(SharedUi_textTextStyle *)headlineLarge headlineMedium:(SharedUi_textTextStyle *)headlineMedium headlineSmall:(SharedUi_textTextStyle *)headlineSmall titleLarge:(SharedUi_textTextStyle *)titleLarge titleMedium:(SharedUi_textTextStyle *)titleMedium titleSmall:(SharedUi_textTextStyle *)titleSmall bodyLarge:(SharedUi_textTextStyle *)bodyLarge bodyMedium:(SharedUi_textTextStyle *)bodyMedium bodySmall:(SharedUi_textTextStyle *)bodySmall labelLarge:(SharedUi_textTextStyle *)labelLarge labelMedium:(SharedUi_textTextStyle *)labelMedium labelSmall:(SharedUi_textTextStyle *)labelSmall __attribute__((swift_name("init(displayLarge:displayMedium:displaySmall:headlineLarge:headlineMedium:headlineSmall:titleLarge:titleMedium:titleSmall:bodyLarge:bodyMedium:bodySmall:labelLarge:labelMedium:labelSmall:)"))) __attribute__((objc_designated_initializer));
- (SharedMaterial3Typography *)doCopyDisplayLarge:(SharedUi_textTextStyle *)displayLarge displayMedium:(SharedUi_textTextStyle *)displayMedium displaySmall:(SharedUi_textTextStyle *)displaySmall headlineLarge:(SharedUi_textTextStyle *)headlineLarge headlineMedium:(SharedUi_textTextStyle *)headlineMedium headlineSmall:(SharedUi_textTextStyle *)headlineSmall titleLarge:(SharedUi_textTextStyle *)titleLarge titleMedium:(SharedUi_textTextStyle *)titleMedium titleSmall:(SharedUi_textTextStyle *)titleSmall bodyLarge:(SharedUi_textTextStyle *)bodyLarge bodyMedium:(SharedUi_textTextStyle *)bodyMedium bodySmall:(SharedUi_textTextStyle *)bodySmall labelLarge:(SharedUi_textTextStyle *)labelLarge labelMedium:(SharedUi_textTextStyle *)labelMedium labelSmall:(SharedUi_textTextStyle *)labelSmall __attribute__((swift_name("doCopy(displayLarge:displayMedium:displaySmall:headlineLarge:headlineMedium:headlineSmall:titleLarge:titleMedium:titleSmall:bodyLarge:bodyMedium:bodySmall:labelLarge:labelMedium:labelSmall:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedUi_textTextStyle *bodyLarge __attribute__((swift_name("bodyLarge")));
@property (readonly) SharedUi_textTextStyle *bodyMedium __attribute__((swift_name("bodyMedium")));
@property (readonly) SharedUi_textTextStyle *bodySmall __attribute__((swift_name("bodySmall")));
@property (readonly) SharedUi_textTextStyle *displayLarge __attribute__((swift_name("displayLarge")));
@property (readonly) SharedUi_textTextStyle *displayMedium __attribute__((swift_name("displayMedium")));
@property (readonly) SharedUi_textTextStyle *displaySmall __attribute__((swift_name("displaySmall")));
@property (readonly) SharedUi_textTextStyle *headlineLarge __attribute__((swift_name("headlineLarge")));
@property (readonly) SharedUi_textTextStyle *headlineMedium __attribute__((swift_name("headlineMedium")));
@property (readonly) SharedUi_textTextStyle *headlineSmall __attribute__((swift_name("headlineSmall")));
@property (readonly) SharedUi_textTextStyle *labelLarge __attribute__((swift_name("labelLarge")));
@property (readonly) SharedUi_textTextStyle *labelMedium __attribute__((swift_name("labelMedium")));
@property (readonly) SharedUi_textTextStyle *labelSmall __attribute__((swift_name("labelSmall")));
@property (readonly) SharedUi_textTextStyle *titleLarge __attribute__((swift_name("titleLarge")));
@property (readonly) SharedUi_textTextStyle *titleMedium __attribute__((swift_name("titleMedium")));
@property (readonly) SharedUi_textTextStyle *titleSmall __attribute__((swift_name("titleSmall")));
@end

__attribute__((swift_name("Koin_coreLockable")))
@interface SharedKoin_coreLockable : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScope")))
@interface SharedKoin_coreScope : SharedKoin_coreLockable
- (instancetype)initWithScopeQualifier:(id<SharedKoin_coreQualifier>)scopeQualifier id:(NSString *)id isRoot:(BOOL)isRoot _koin:(SharedKoin_coreKoin *)_koin __attribute__((swift_name("init(scopeQualifier:id:isRoot:_koin:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)close __attribute__((swift_name("close()")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<SharedKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (id)getQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (id _Nullable)getClazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (NSArray<id> *)getAllClazz:(id<SharedKotlinKClass>)clazz __attribute__((swift_name("getAll(clazz:)")));
- (SharedKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
- (id _Nullable)getOrNullQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id _Nullable)getOrNullClazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (id _Nullable)getPropertyOrNullKey:(NSString *)key __attribute__((swift_name("getPropertyOrNull(key:)")));
- (SharedKoin_coreScope *)getScopeScopeID:(NSString *)scopeID __attribute__((swift_name("getScope(scopeID:)")));
- (id _Nullable)getSource __attribute__((swift_name("getSource()")));
- (id _Nullable)getWithParametersClazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder * _Nullable)parameters __attribute__((swift_name("getWithParameters(clazz:qualifier:parameters:)")));
- (id<SharedKotlinLazy>)injectQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier mode:(SharedKotlinLazyThreadSafetyMode *)mode parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<SharedKotlinLazy>)injectOrNullQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier mode:(SharedKotlinLazyThreadSafetyMode *)mode parameters:(SharedKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (BOOL)isNotClosed __attribute__((swift_name("isNotClosed()")));
- (void)linkToScopes:(SharedKotlinArray<SharedKoin_coreScope *> *)scopes __attribute__((swift_name("linkTo(scopes:)")));
- (void)registerCallbackCallback:(id<SharedKoin_coreScopeCallback>)callback __attribute__((swift_name("registerCallback(callback:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (void)unlinkScopes:(SharedKotlinArray<SharedKoin_coreScope *> *)scopes __attribute__((swift_name("unlink(scopes:)")));
@property (readonly) BOOL closed __attribute__((swift_name("closed")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isRoot __attribute__((swift_name("isRoot")));
@property (readonly) SharedKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) id<SharedKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@property id _Nullable sourceValue __attribute__((swift_name("sourceValue")));
@end

__attribute__((swift_name("Koin_coreKoinScopeComponent")))
@protocol SharedKoin_coreKoinScopeComponent <SharedKoin_coreKoinComponent>
@required
@property (readonly) SharedKoin_coreScope *scope __attribute__((swift_name("scope")));
@end

__attribute__((swift_name("Koin_coreQualifier")))
@protocol SharedKoin_coreQualifier
@required
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol SharedKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol SharedKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol SharedKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol SharedKotlinKClass <SharedKotlinKDeclarationContainer, SharedKotlinKAnnotatedElement, SharedKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

__attribute__((swift_name("Koin_coreParametersHolder")))
@interface SharedKoin_coreParametersHolder : SharedBase
- (instancetype)initWith_values:(NSMutableArray<id> *)_values useIndexedValues:(SharedBoolean * _Nullable)useIndexedValues __attribute__((swift_name("init(_values:useIndexedValues:)"))) __attribute__((objc_designated_initializer));
- (SharedKoin_coreParametersHolder *)addValue:(id)value __attribute__((swift_name("add(value:)")));
- (id _Nullable)component1 __attribute__((swift_name("component1()")));
- (id _Nullable)component2 __attribute__((swift_name("component2()")));
- (id _Nullable)component3 __attribute__((swift_name("component3()")));
- (id _Nullable)component4 __attribute__((swift_name("component4()")));
- (id _Nullable)component5 __attribute__((swift_name("component5()")));
- (id _Nullable)elementAtI:(int32_t)i clazz:(id<SharedKotlinKClass>)clazz __attribute__((swift_name("elementAt(i:clazz:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (id)get __attribute__((swift_name("get()")));
- (id _Nullable)getI:(int32_t)i __attribute__((swift_name("get(i:)")));
- (id _Nullable)getOrNull __attribute__((swift_name("getOrNull()")));
- (id _Nullable)getOrNullClazz:(id<SharedKotlinKClass>)clazz __attribute__((swift_name("getOrNull(clazz:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedKoin_coreParametersHolder *)insertIndex:(int32_t)index value:(id)value __attribute__((swift_name("insert(index:value:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isNotEmpty __attribute__((swift_name("isNotEmpty()")));
- (void)setI:(int32_t)i t:(id _Nullable)t __attribute__((swift_name("set(i:t:)")));
- (int32_t)size __attribute__((swift_name("size()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property int32_t index __attribute__((swift_name("index")));
@property (readonly) SharedBoolean * _Nullable useIndexedValues __attribute__((swift_name("useIndexedValues")));
@property (readonly) NSArray<id> *values __attribute__((swift_name("values")));
@end

__attribute__((swift_name("KotlinLazy")))
@protocol SharedKotlinLazy
@required
- (BOOL)isInitialized __attribute__((swift_name("isInitialized()")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinLazyThreadSafetyMode")))
@interface SharedKotlinLazyThreadSafetyMode : SharedKotlinEnum<SharedKotlinLazyThreadSafetyMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKotlinLazyThreadSafetyMode *synchronized __attribute__((swift_name("synchronized")));
@property (class, readonly) SharedKotlinLazyThreadSafetyMode *publication __attribute__((swift_name("publication")));
@property (class, readonly) SharedKotlinLazyThreadSafetyMode *none __attribute__((swift_name("none")));
+ (SharedKotlinArray<SharedKotlinLazyThreadSafetyMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKotlinLazyThreadSafetyMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((swift_name("Koin_coreLogger")))
@interface SharedKoin_coreLogger : SharedBase
- (instancetype)initWithLevel:(SharedKoin_coreLevel *)level __attribute__((swift_name("init(level:)"))) __attribute__((objc_designated_initializer));
- (void)debugMsg:(NSString *)msg __attribute__((swift_name("debug(msg:)")));
- (void)displayLevel:(SharedKoin_coreLevel *)level msg:(NSString *)msg __attribute__((swift_name("display(level:msg:)")));
- (void)errorMsg:(NSString *)msg __attribute__((swift_name("error(msg:)")));
- (void)infoMsg:(NSString *)msg __attribute__((swift_name("info(msg:)")));
- (BOOL)isAtLvl:(SharedKoin_coreLevel *)lvl __attribute__((swift_name("isAt(lvl:)")));
- (void)logLvl:(SharedKoin_coreLevel *)lvl msg:(NSString *(^)(void))msg __attribute__((swift_name("log(lvl:msg:)")));
- (void)logLvl:(SharedKoin_coreLevel *)lvl msg_:(NSString *)msg __attribute__((swift_name("log(lvl:msg_:)")));
- (void)warnMsg:(NSString *)msg __attribute__((swift_name("warn(msg:)")));
@property SharedKoin_coreLevel *level __attribute__((swift_name("level")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreExtensionManager")))
@interface SharedKoin_coreExtensionManager : SharedBase
- (instancetype)initWith_koin:(SharedKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (id<SharedKoin_coreKoinExtension>)getExtensionId:(NSString *)id __attribute__((swift_name("getExtension(id:)")));
- (id<SharedKoin_coreKoinExtension> _Nullable)getExtensionOrNullId:(NSString *)id __attribute__((swift_name("getExtensionOrNull(id:)")));
- (void)registerExtensionId:(NSString *)id extension:(id<SharedKoin_coreKoinExtension>)extension __attribute__((swift_name("registerExtension(id:extension:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceRegistry")))
@interface SharedKoin_coreInstanceRegistry : SharedBase
- (instancetype)initWith_koin:(SharedKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)saveMappingAllowOverride:(BOOL)allowOverride mapping:(NSString *)mapping factory:(SharedKoin_coreInstanceFactory<id> *)factory logWarning:(BOOL)logWarning __attribute__((swift_name("saveMapping(allowOverride:mapping:factory:logWarning:)")));
- (int32_t)size __attribute__((swift_name("size()")));
@property (readonly) SharedKoin_coreKoin *_koin __attribute__((swift_name("_koin")));
@property (readonly) NSDictionary<NSString *, SharedKoin_coreInstanceFactory<id> *> *instances __attribute__((swift_name("instances")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_corePropertyRegistry")))
@interface SharedKoin_corePropertyRegistry : SharedBase
- (instancetype)initWith_koin:(SharedKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (void)savePropertiesProperties:(NSDictionary<NSString *, id> *)properties __attribute__((swift_name("saveProperties(properties:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry")))
@interface SharedKoin_coreScopeRegistry : SharedBase
- (instancetype)initWith_koin:(SharedKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKoin_coreScopeRegistryCompanion *companion __attribute__((swift_name("companion")));
- (void)loadScopesModules:(NSSet<SharedKoin_coreModule *> *)modules __attribute__((swift_name("loadScopes(modules:)")));
@property (readonly) SharedKoin_coreScope *rootScope __attribute__((swift_name("rootScope")));
@property (readonly) NSSet<id<SharedKoin_coreQualifier>> *scopeDefinitions __attribute__((swift_name("scopeDefinitions")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeInstant.Companion")))
@interface SharedKotlinx_datetimeInstantCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_datetimeInstantCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinx_datetimeInstant *)fromEpochMillisecondsEpochMilliseconds:(int64_t)epochMilliseconds __attribute__((swift_name("fromEpochMilliseconds(epochMilliseconds:)")));
- (SharedKotlinx_datetimeInstant *)fromEpochSecondsEpochSeconds:(int64_t)epochSeconds nanosecondAdjustment:(int32_t)nanosecondAdjustment __attribute__((swift_name("fromEpochSeconds(epochSeconds:nanosecondAdjustment:)")));
- (SharedKotlinx_datetimeInstant *)fromEpochSecondsEpochSeconds:(int64_t)epochSeconds nanosecondAdjustment_:(int64_t)nanosecondAdjustment __attribute__((swift_name("fromEpochSeconds(epochSeconds:nanosecondAdjustment_:)")));
- (SharedKotlinx_datetimeInstant *)now __attribute__((swift_name("now()"))) __attribute__((unavailable("Use Clock.System.now() instead")));
- (SharedKotlinx_datetimeInstant *)parseInput:(id)input format:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("parse(input:format:)")));
- (id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@property (readonly) SharedKotlinx_datetimeInstant *DISTANT_FUTURE __attribute__((swift_name("DISTANT_FUTURE")));
@property (readonly) SharedKotlinx_datetimeInstant *DISTANT_PAST __attribute__((swift_name("DISTANT_PAST")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/datetime/serializers/LocalTimeIso8601Serializer))
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeLocalTime")))
@interface SharedKotlinx_datetimeLocalTime : SharedBase <SharedKotlinComparable>
- (instancetype)initWithHour:(int32_t)hour minute:(int32_t)minute second:(int32_t)second nanosecond:(int32_t)nanosecond __attribute__((swift_name("init(hour:minute:second:nanosecond:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinx_datetimeLocalTimeCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedKotlinx_datetimeLocalTime *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (int32_t)toMillisecondOfDay __attribute__((swift_name("toMillisecondOfDay()")));
- (int64_t)toNanosecondOfDay __attribute__((swift_name("toNanosecondOfDay()")));
- (int32_t)toSecondOfDay __attribute__((swift_name("toSecondOfDay()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t hour __attribute__((swift_name("hour")));
@property (readonly) int32_t minute __attribute__((swift_name("minute")));
@property (readonly) int32_t nanosecond __attribute__((swift_name("nanosecond")));
@property (readonly) int32_t second __attribute__((swift_name("second")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeMonth")))
@interface SharedKotlinx_datetimeMonth : SharedKotlinEnum<SharedKotlinx_datetimeMonth *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKotlinx_datetimeMonth *january __attribute__((swift_name("january")));
@property (class, readonly) SharedKotlinx_datetimeMonth *february __attribute__((swift_name("february")));
@property (class, readonly) SharedKotlinx_datetimeMonth *march __attribute__((swift_name("march")));
@property (class, readonly) SharedKotlinx_datetimeMonth *april __attribute__((swift_name("april")));
@property (class, readonly) SharedKotlinx_datetimeMonth *may __attribute__((swift_name("may")));
@property (class, readonly) SharedKotlinx_datetimeMonth *june __attribute__((swift_name("june")));
@property (class, readonly) SharedKotlinx_datetimeMonth *july __attribute__((swift_name("july")));
@property (class, readonly) SharedKotlinx_datetimeMonth *august __attribute__((swift_name("august")));
@property (class, readonly) SharedKotlinx_datetimeMonth *september __attribute__((swift_name("september")));
@property (class, readonly) SharedKotlinx_datetimeMonth *october __attribute__((swift_name("october")));
@property (class, readonly) SharedKotlinx_datetimeMonth *november __attribute__((swift_name("november")));
@property (class, readonly) SharedKotlinx_datetimeMonth *december __attribute__((swift_name("december")));
+ (SharedKotlinArray<SharedKotlinx_datetimeMonth *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKotlinx_datetimeMonth *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeLocalDateTime.Companion")))
@interface SharedKotlinx_datetimeLocalDateTimeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_datetimeLocalDateTimeCompanion *shared __attribute__((swift_name("shared")));
- (id<SharedKotlinx_datetimeDateTimeFormat>)FormatBuilder:(void (^)(id<SharedKotlinx_datetimeDateTimeFormatBuilderWithDateTime>))builder __attribute__((swift_name("Format(builder:)")));
- (SharedKotlinx_datetimeLocalDateTime *)parseInput:(id)input format:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("parse(input:format:)")));
- (id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeDayOfWeek")))
@interface SharedKotlinx_datetimeDayOfWeek : SharedKotlinEnum<SharedKotlinx_datetimeDayOfWeek *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *monday __attribute__((swift_name("monday")));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *tuesday __attribute__((swift_name("tuesday")));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *wednesday __attribute__((swift_name("wednesday")));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *thursday __attribute__((swift_name("thursday")));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *friday __attribute__((swift_name("friday")));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *saturday __attribute__((swift_name("saturday")));
@property (class, readonly) SharedKotlinx_datetimeDayOfWeek *sunday __attribute__((swift_name("sunday")));
+ (SharedKotlinArray<SharedKotlinx_datetimeDayOfWeek *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKotlinx_datetimeDayOfWeek *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeLocalDate.Companion")))
@interface SharedKotlinx_datetimeLocalDateCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_datetimeLocalDateCompanion *shared __attribute__((swift_name("shared")));
- (id<SharedKotlinx_datetimeDateTimeFormat>)FormatBlock:(void (^)(id<SharedKotlinx_datetimeDateTimeFormatBuilderWithDate>))block __attribute__((swift_name("Format(block:)")));
- (SharedKotlinx_datetimeLocalDate *)fromEpochDaysEpochDays:(int32_t)epochDays __attribute__((swift_name("fromEpochDays(epochDays:)")));
- (SharedKotlinx_datetimeLocalDate *)parseInput:(id)input format:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("parse(input:format:)")));
- (id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((swift_name("RuntimeQueryListener")))
@protocol SharedRuntimeQueryListener
@required
- (void)queryResultsChanged __attribute__((swift_name("queryResultsChanged()")));
@end

__attribute__((swift_name("RuntimeQueryResult")))
@protocol SharedRuntimeQueryResult
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)awaitWithCompletionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("await(completionHandler:)")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("RuntimeSqlPreparedStatement")))
@protocol SharedRuntimeSqlPreparedStatement
@required
- (void)bindBooleanIndex:(int32_t)index boolean:(SharedBoolean * _Nullable)boolean __attribute__((swift_name("bindBoolean(index:boolean:)")));
- (void)bindBytesIndex:(int32_t)index bytes:(SharedKotlinByteArray * _Nullable)bytes __attribute__((swift_name("bindBytes(index:bytes:)")));
- (void)bindDoubleIndex:(int32_t)index double:(SharedDouble * _Nullable)double_ __attribute__((swift_name("bindDouble(index:double:)")));
- (void)bindLongIndex:(int32_t)index long:(SharedLong * _Nullable)long_ __attribute__((swift_name("bindLong(index:long:)")));
- (void)bindStringIndex:(int32_t)index string:(NSString * _Nullable)string __attribute__((swift_name("bindString(index:string:)")));
@end

__attribute__((swift_name("RuntimeSqlCursor")))
@protocol SharedRuntimeSqlCursor
@required
- (SharedBoolean * _Nullable)getBooleanIndex:(int32_t)index __attribute__((swift_name("getBoolean(index:)")));
- (SharedKotlinByteArray * _Nullable)getBytesIndex:(int32_t)index __attribute__((swift_name("getBytes(index:)")));
- (SharedDouble * _Nullable)getDoubleIndex:(int32_t)index __attribute__((swift_name("getDouble(index:)")));
- (SharedLong * _Nullable)getLongIndex:(int32_t)index __attribute__((swift_name("getLong(index:)")));
- (NSString * _Nullable)getStringIndex:(int32_t)index __attribute__((swift_name("getString(index:)")));
- (id<SharedRuntimeQueryResult>)next __attribute__((swift_name("next()")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinCoroutineContext")))
@protocol SharedKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<SharedKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<SharedKotlinCoroutineContextElement> _Nullable)getKey:(id<SharedKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<SharedKotlinCoroutineContext>)minusKeyKey:(id<SharedKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<SharedKotlinCoroutineContext>)plusContext:(id<SharedKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end

__attribute__((swift_name("Ktor_client_coreHttpClientEngine")))
@protocol SharedKtor_client_coreHttpClientEngine <SharedKotlinx_coroutines_coreCoroutineScope, SharedKtor_ioCloseable>
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeData:(SharedKtor_client_coreHttpRequestData *)data completionHandler:(void (^)(SharedKtor_client_coreHttpResponseData * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(data:completionHandler:)")));
- (void)installClient:(SharedKtor_client_coreHttpClient *)client __attribute__((swift_name("install(client:)")));
@property (readonly) SharedKtor_client_coreHttpClientEngineConfig *config __attribute__((swift_name("config")));
@property (readonly) SharedKotlinx_coroutines_coreCoroutineDispatcher *dispatcher __attribute__((swift_name("dispatcher")));
@property (readonly) NSSet<id<SharedKtor_client_coreHttpClientEngineCapability>> *supportedCapabilities __attribute__((swift_name("supportedCapabilities")));
@end

__attribute__((swift_name("Ktor_client_coreHttpClientEngineConfig")))
@interface SharedKtor_client_coreHttpClientEngineConfig : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property SharedKotlinx_coroutines_coreCoroutineDispatcher * _Nullable dispatcher __attribute__((swift_name("dispatcher")));
@property BOOL pipelining __attribute__((swift_name("pipelining")));
@property SharedKtor_client_coreProxyConfig * _Nullable proxy __attribute__((swift_name("proxy")));
@property int32_t threadsCount __attribute__((swift_name("threadsCount"))) __attribute__((unavailable("The [threadsCount] property is deprecated. Consider setting [dispatcher] instead.")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpClientConfig")))
@interface SharedKtor_client_coreHttpClientConfig<T> : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (SharedKtor_client_coreHttpClientConfig<T> *)clone __attribute__((swift_name("clone()")));
- (void)engineBlock:(void (^)(T))block __attribute__((swift_name("engine(block:)")));
- (void)installClient:(SharedKtor_client_coreHttpClient *)client __attribute__((swift_name("install(client:)")));
- (void)installPlugin:(id<SharedKtor_client_coreHttpClientPlugin>)plugin configure:(void (^)(id))configure __attribute__((swift_name("install(plugin:configure:)")));
- (void)installKey:(NSString *)key block:(void (^)(SharedKtor_client_coreHttpClient *))block __attribute__((swift_name("install(key:block:)")));
- (void)plusAssignOther:(SharedKtor_client_coreHttpClientConfig<T> *)other __attribute__((swift_name("plusAssign(other:)")));
@property BOOL developmentMode __attribute__((swift_name("developmentMode"))) __attribute__((deprecated("Development mode is no longer required. The property will be removed in the future.")));
@property BOOL expectSuccess __attribute__((swift_name("expectSuccess")));
@property BOOL followRedirects __attribute__((swift_name("followRedirects")));
@property BOOL useDefaultTransformers __attribute__((swift_name("useDefaultTransformers")));
@end

__attribute__((swift_name("Ktor_client_coreHttpClientEngineCapability")))
@protocol SharedKtor_client_coreHttpClientEngineCapability
@required
@end

__attribute__((swift_name("Ktor_utilsAttributes")))
@protocol SharedKtor_utilsAttributes
@required
- (id)computeIfAbsentKey:(SharedKtor_utilsAttributeKey<id> *)key block:(id (^)(void))block __attribute__((swift_name("computeIfAbsent(key:block:)")));
- (BOOL)containsKey:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("contains(key:)")));
- (id)getKey_:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("get(key_:)")));
- (id _Nullable)getOrNullKey:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("getOrNull(key:)")));
- (void)putKey:(SharedKtor_utilsAttributeKey<id> *)key value:(id)value __attribute__((swift_name("put(key:value:)")));
- (void)removeKey:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("remove(key:)")));
- (id)takeKey:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("take(key:)")));
- (id _Nullable)takeOrNullKey:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("takeOrNull(key:)")));
@property (readonly) NSArray<SharedKtor_utilsAttributeKey<id> *> *allKeys __attribute__((swift_name("allKeys")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_eventsEvents")))
@interface SharedKtor_eventsEvents : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)raiseDefinition:(SharedKtor_eventsEventDefinition<id> *)definition value:(id _Nullable)value __attribute__((swift_name("raise(definition:value:)")));
- (id<SharedKotlinx_coroutines_coreDisposableHandle>)subscribeDefinition:(SharedKtor_eventsEventDefinition<id> *)definition handler:(void (^)(id _Nullable))handler __attribute__((swift_name("subscribe(definition:handler:)")));
- (void)unsubscribeDefinition:(SharedKtor_eventsEventDefinition<id> *)definition handler:(void (^)(id _Nullable))handler __attribute__((swift_name("unsubscribe(definition:handler:)")));
@end

__attribute__((swift_name("Ktor_utilsPipeline")))
@interface SharedKtor_utilsPipeline<TSubject, TContext> : SharedBase
- (instancetype)initWithPhases:(SharedKotlinArray<SharedKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhase:(SharedKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<SharedKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer));
- (void)addPhasePhase:(SharedKtor_utilsPipelinePhase *)phase __attribute__((swift_name("addPhase(phase:)")));
- (void)afterIntercepted __attribute__((swift_name("afterIntercepted()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeContext:(TContext)context subject:(TSubject)subject completionHandler:(void (^)(TSubject _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(context:subject:completionHandler:)")));
- (void)insertPhaseAfterReference:(SharedKtor_utilsPipelinePhase *)reference phase:(SharedKtor_utilsPipelinePhase *)phase __attribute__((swift_name("insertPhaseAfter(reference:phase:)")));
- (void)insertPhaseBeforeReference:(SharedKtor_utilsPipelinePhase *)reference phase:(SharedKtor_utilsPipelinePhase *)phase __attribute__((swift_name("insertPhaseBefore(reference:phase:)")));
- (void)interceptPhase:(SharedKtor_utilsPipelinePhase *)phase block:(id<SharedKotlinSuspendFunction2>)block __attribute__((swift_name("intercept(phase:block:)")));
- (NSArray<id<SharedKotlinSuspendFunction2>> *)interceptorsForPhasePhase:(SharedKtor_utilsPipelinePhase *)phase __attribute__((swift_name("interceptorsForPhase(phase:)")));
- (void)mergeFrom:(SharedKtor_utilsPipeline<TSubject, TContext> *)from __attribute__((swift_name("merge(from:)")));
- (void)mergePhasesFrom:(SharedKtor_utilsPipeline<TSubject, TContext> *)from __attribute__((swift_name("mergePhases(from:)")));
- (void)resetFromFrom:(SharedKtor_utilsPipeline<TSubject, TContext> *)from __attribute__((swift_name("resetFrom(from:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<SharedKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) NSArray<SharedKtor_utilsPipelinePhase *> *items __attribute__((swift_name("items")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpReceivePipeline")))
@interface SharedKtor_client_coreHttpReceivePipeline : SharedKtor_utilsPipeline<SharedKtor_client_coreHttpResponse *, SharedKotlinUnit *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhases:(SharedKotlinArray<SharedKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhase:(SharedKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<SharedKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_client_coreHttpReceivePipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestPipeline")))
@interface SharedKtor_client_coreHttpRequestPipeline : SharedKtor_utilsPipeline<id, SharedKtor_client_coreHttpRequestBuilder *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhases:(SharedKotlinArray<SharedKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhase:(SharedKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<SharedKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_client_coreHttpRequestPipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponsePipeline")))
@interface SharedKtor_client_coreHttpResponsePipeline : SharedKtor_utilsPipeline<SharedKtor_client_coreHttpResponseContainer *, SharedKtor_client_coreHttpClientCall *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhases:(SharedKotlinArray<SharedKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhase:(SharedKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<SharedKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_client_coreHttpResponsePipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpSendPipeline")))
@interface SharedKtor_client_coreHttpSendPipeline : SharedKtor_utilsPipeline<id, SharedKtor_client_coreHttpRequestBuilder *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhases:(SharedKotlinArray<SharedKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhase:(SharedKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<SharedKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_client_coreHttpSendPipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol SharedKotlinx_serialization_coreEncoder
@required
- (id<SharedKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<SharedKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<SharedKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<SharedKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<SharedKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) SharedKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol SharedKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<SharedKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<SharedKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<SharedKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) SharedKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol SharedKotlinx_serialization_coreDecoder
@required
- (id<SharedKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<SharedKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (SharedKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<SharedKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<SharedKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) SharedKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol SharedKotlinx_coroutines_coreFlowCollector
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol SharedKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RuntimeAfterVersion")))
@interface SharedRuntimeAfterVersion : SharedBase
- (instancetype)initWithAfterVersion:(int64_t)afterVersion block:(void (^)(id<SharedRuntimeSqlDriver>))block __attribute__((swift_name("init(afterVersion:block:)"))) __attribute__((objc_designated_initializer));
@property (readonly) int64_t afterVersion __attribute__((swift_name("afterVersion")));
@property (readonly) void (^block)(id<SharedRuntimeSqlDriver>) __attribute__((swift_name("block")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinDefinition")))
@interface SharedKoin_coreKoinDefinition<R> : SharedBase
- (instancetype)initWithModule:(SharedKoin_coreModule *)module factory:(SharedKoin_coreInstanceFactory<R> *)factory __attribute__((swift_name("init(module:factory:)"))) __attribute__((objc_designated_initializer));
- (SharedKoin_coreKoinDefinition<R> *)doCopyModule:(SharedKoin_coreModule *)module factory:(SharedKoin_coreInstanceFactory<R> *)factory __attribute__((swift_name("doCopy(module:factory:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKoin_coreInstanceFactory<R> *factory __attribute__((swift_name("factory")));
@property (readonly) SharedKoin_coreModule *module __attribute__((swift_name("module")));
@end

__attribute__((swift_name("Koin_coreInstanceFactory")))
@interface SharedKoin_coreInstanceFactory<T> : SharedKoin_coreLockable
- (instancetype)initWithBeanDefinition:(SharedKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKoin_coreInstanceFactoryCompanion *companion __attribute__((swift_name("companion")));
- (T _Nullable)createContext:(SharedKoin_coreResolutionContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(SharedKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(SharedKoin_coreResolutionContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(SharedKoin_coreResolutionContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@property (readonly) SharedKoin_coreBeanDefinition<T> *beanDefinition __attribute__((swift_name("beanDefinition")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreSingleInstanceFactory")))
@interface SharedKoin_coreSingleInstanceFactory<T> : SharedKoin_coreInstanceFactory<T>
- (instancetype)initWithBeanDefinition:(SharedKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (T _Nullable)createContext:(SharedKoin_coreResolutionContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(SharedKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(SharedKoin_coreResolutionContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(SharedKoin_coreResolutionContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeDSL")))
@interface SharedKoin_coreScopeDSL : SharedBase
- (instancetype)initWithScopeQualifier:(id<SharedKoin_coreQualifier>)scopeQualifier module:(SharedKoin_coreModule *)module __attribute__((swift_name("init(scopeQualifier:module:)"))) __attribute__((objc_designated_initializer));
- (SharedKoin_coreKoinDefinition<id> *)factoryQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(SharedKoin_coreScope *, SharedKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (SharedKoin_coreKoinDefinition<id> *)scopedQualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(SharedKoin_coreScope *, SharedKoin_coreParametersHolder *))definition __attribute__((swift_name("scoped(qualifier:definition:)")));
@property (readonly) SharedKoin_coreModule *module __attribute__((swift_name("module")));
@property (readonly) id<SharedKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextStyle")))
@interface SharedUi_textTextStyle : SharedBase
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle textAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent platformStyle:(SharedUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:drawStyle:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:textMotion:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithBrush:(SharedUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle textAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent platformStyle:(SharedUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("init(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:drawStyle:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:textMotion:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textTextStyleCompanion *companion __attribute__((swift_name("companion")));
- (SharedUi_textTextStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle textAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent platformStyle:(SharedUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:drawStyle:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:textMotion:)")));
- (SharedUi_textTextStyle *)doCopyBrush:(SharedUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle textAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent platformStyle:(SharedUi_textPlatformTextStyle * _Nullable)platformStyle lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("doCopy(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:drawStyle:textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:textMotion:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasSameDrawAffectingAttributesOther:(SharedUi_textTextStyle *)other __attribute__((swift_name("hasSameDrawAffectingAttributes(other:)")));
- (BOOL)hasSameLayoutAffectingAttributesOther:(SharedUi_textTextStyle *)other __attribute__((swift_name("hasSameLayoutAffectingAttributes(other:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)mergeOther:(SharedUi_textParagraphStyle *)other __attribute__((swift_name("merge(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)mergeOther_:(SharedUi_textSpanStyle *)other __attribute__((swift_name("merge(other_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)mergeOther__:(SharedUi_textTextStyle * _Nullable)other __attribute__((swift_name("merge(other__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)mergeColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle textAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens platformStyle:(SharedUi_textPlatformTextStyle * _Nullable)platformStyle textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("merge(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:drawStyle:textAlign:textDirection:lineHeight:textIndent:lineHeightStyle:lineBreak:hyphens:platformStyle:textMotion:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)plusOther:(SharedUi_textParagraphStyle *)other __attribute__((swift_name("plus(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)plusOther_:(SharedUi_textSpanStyle *)other __attribute__((swift_name("plus(other_:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textTextStyle *)plusOther__:(SharedUi_textTextStyle *)other __attribute__((swift_name("plus(other__:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textParagraphStyle *)toParagraphStyle __attribute__((swift_name("toParagraphStyle()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textSpanStyle *)toSpanStyle __attribute__((swift_name("toSpanStyle()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) uint64_t background __attribute__((swift_name("background")));
@property (readonly) id _Nullable baselineShift __attribute__((swift_name("baselineShift")));
@property (readonly) SharedUi_graphicsBrush * _Nullable brush __attribute__((swift_name("brush")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) id _Nullable deprecated_boxing_hyphens __attribute__((swift_name("deprecated_boxing_hyphens"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) id _Nullable deprecated_boxing_lineBreak __attribute__((swift_name("deprecated_boxing_lineBreak"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) id _Nullable deprecated_boxing_textAlign __attribute__((swift_name("deprecated_boxing_textAlign"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) id _Nullable deprecated_boxing_textDirection __attribute__((swift_name("deprecated_boxing_textDirection"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) SharedUi_graphicsDrawStyle * _Nullable drawStyle __attribute__((swift_name("drawStyle")));
@property (readonly) SharedUi_textFontFamily * _Nullable fontFamily __attribute__((swift_name("fontFamily")));
@property (readonly) NSString * _Nullable fontFeatureSettings __attribute__((swift_name("fontFeatureSettings")));
@property (readonly) int64_t fontSize __attribute__((swift_name("fontSize")));
@property (readonly) id _Nullable fontStyle __attribute__((swift_name("fontStyle")));
@property (readonly) id _Nullable fontSynthesis __attribute__((swift_name("fontSynthesis")));
@property (readonly) SharedUi_textFontWeight * _Nullable fontWeight __attribute__((swift_name("fontWeight")));
@property (readonly) int32_t hyphens __attribute__((swift_name("hyphens")));
@property (readonly) int64_t letterSpacing __attribute__((swift_name("letterSpacing")));
@property (readonly) int32_t lineBreak __attribute__((swift_name("lineBreak")));
@property (readonly) int64_t lineHeight __attribute__((swift_name("lineHeight")));
@property (readonly) SharedUi_textLineHeightStyle * _Nullable lineHeightStyle __attribute__((swift_name("lineHeightStyle")));
@property (readonly) SharedUi_textLocaleList * _Nullable localeList __attribute__((swift_name("localeList")));
@property (readonly) SharedUi_textPlatformTextStyle * _Nullable platformStyle __attribute__((swift_name("platformStyle")));
@property (readonly) SharedUi_graphicsShadow * _Nullable shadow __attribute__((swift_name("shadow")));
@property (readonly) int32_t textAlign __attribute__((swift_name("textAlign")));
@property (readonly) SharedUi_textTextDecoration * _Nullable textDecoration __attribute__((swift_name("textDecoration")));
@property (readonly) int32_t textDirection __attribute__((swift_name("textDirection")));
@property (readonly) SharedUi_textTextGeometricTransform * _Nullable textGeometricTransform __attribute__((swift_name("textGeometricTransform")));
@property (readonly) SharedUi_textTextIndent * _Nullable textIndent __attribute__((swift_name("textIndent")));
@property (readonly) SharedUi_textTextMotion * _Nullable textMotion __attribute__((swift_name("textMotion")));
@end

__attribute__((swift_name("Koin_coreScopeCallback")))
@protocol SharedKoin_coreScopeCallback
@required
- (void)onScopeCloseScope:(SharedKoin_coreScope *)scope __attribute__((swift_name("onScopeClose(scope:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreLevel")))
@interface SharedKoin_coreLevel : SharedKotlinEnum<SharedKoin_coreLevel *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKoin_coreLevel *debug __attribute__((swift_name("debug")));
@property (class, readonly) SharedKoin_coreLevel *info __attribute__((swift_name("info")));
@property (class, readonly) SharedKoin_coreLevel *warning __attribute__((swift_name("warning")));
@property (class, readonly) SharedKoin_coreLevel *error __attribute__((swift_name("error")));
@property (class, readonly) SharedKoin_coreLevel *none __attribute__((swift_name("none")));
+ (SharedKotlinArray<SharedKoin_coreLevel *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKoin_coreLevel *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((swift_name("Koin_coreKoinExtension")))
@protocol SharedKoin_coreKoinExtension
@required
- (void)onClose __attribute__((swift_name("onClose()")));
- (void)onRegisterKoin:(SharedKoin_coreKoin *)koin __attribute__((swift_name("onRegister(koin:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry.Companion")))
@interface SharedKoin_coreScopeRegistryCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKoin_coreScopeRegistryCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Kotlinx_datetimeDateTimeFormat")))
@protocol SharedKotlinx_datetimeDateTimeFormat
@required
- (NSString *)formatValue:(id _Nullable)value __attribute__((swift_name("format(value:)")));
- (id<SharedKotlinAppendable>)formatToAppendable:(id<SharedKotlinAppendable>)appendable value:(id _Nullable)value __attribute__((swift_name("formatTo(appendable:value:)")));
- (id _Nullable)parseInput:(id)input __attribute__((swift_name("parse(input:)")));
- (id _Nullable)parseOrNullInput:(id)input __attribute__((swift_name("parseOrNull(input:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeLocalTime.Companion")))
@interface SharedKotlinx_datetimeLocalTimeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_datetimeLocalTimeCompanion *shared __attribute__((swift_name("shared")));
- (id<SharedKotlinx_datetimeDateTimeFormat>)FormatBuilder:(void (^)(id<SharedKotlinx_datetimeDateTimeFormatBuilderWithTime>))builder __attribute__((swift_name("Format(builder:)")));
- (SharedKotlinx_datetimeLocalTime *)fromMillisecondOfDayMillisecondOfDay:(int32_t)millisecondOfDay __attribute__((swift_name("fromMillisecondOfDay(millisecondOfDay:)")));
- (SharedKotlinx_datetimeLocalTime *)fromNanosecondOfDayNanosecondOfDay:(int64_t)nanosecondOfDay __attribute__((swift_name("fromNanosecondOfDay(nanosecondOfDay:)")));
- (SharedKotlinx_datetimeLocalTime *)fromSecondOfDaySecondOfDay:(int32_t)secondOfDay __attribute__((swift_name("fromSecondOfDay(secondOfDay:)")));
- (SharedKotlinx_datetimeLocalTime *)parseInput:(id)input format:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("parse(input:format:)")));
- (id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((swift_name("Kotlinx_datetimeDateTimeFormatBuilder")))
@protocol SharedKotlinx_datetimeDateTimeFormatBuilder
@required
- (void)charsValue:(NSString *)value __attribute__((swift_name("chars(value:)")));
@end

__attribute__((swift_name("Kotlinx_datetimeDateTimeFormatBuilderWithDate")))
@protocol SharedKotlinx_datetimeDateTimeFormatBuilderWithDate <SharedKotlinx_datetimeDateTimeFormatBuilder>
@required
- (void)dateFormat:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("date(format:)")));
- (void)dayOfMonthPadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("dayOfMonth(padding:)")));
- (void)dayOfWeekNames:(SharedKotlinx_datetimeDayOfWeekNames *)names __attribute__((swift_name("dayOfWeek(names:)")));
- (void)monthNameNames:(SharedKotlinx_datetimeMonthNames *)names __attribute__((swift_name("monthName(names:)")));
- (void)monthNumberPadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("monthNumber(padding:)")));
- (void)yearPadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("year(padding:)")));
- (void)yearTwoDigitsBaseYear:(int32_t)baseYear __attribute__((swift_name("yearTwoDigits(baseYear:)")));
@end

__attribute__((swift_name("Kotlinx_datetimeDateTimeFormatBuilderWithTime")))
@protocol SharedKotlinx_datetimeDateTimeFormatBuilderWithTime <SharedKotlinx_datetimeDateTimeFormatBuilder>
@required
- (void)amPmHourPadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("amPmHour(padding:)")));
- (void)amPmMarkerAm:(NSString *)am pm:(NSString *)pm __attribute__((swift_name("amPmMarker(am:pm:)")));
- (void)hourPadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("hour(padding:)")));
- (void)minutePadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("minute(padding:)")));
- (void)secondPadding:(SharedKotlinx_datetimePadding *)padding __attribute__((swift_name("second(padding:)")));
- (void)secondFractionFixedLength:(int32_t)fixedLength __attribute__((swift_name("secondFraction(fixedLength:)")));
- (void)secondFractionMinLength:(int32_t)minLength maxLength:(int32_t)maxLength __attribute__((swift_name("secondFraction(minLength:maxLength:)")));
- (void)timeFormat:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("time(format:)")));
@end

__attribute__((swift_name("Kotlinx_datetimeDateTimeFormatBuilderWithDateTime")))
@protocol SharedKotlinx_datetimeDateTimeFormatBuilderWithDateTime <SharedKotlinx_datetimeDateTimeFormatBuilderWithDate, SharedKotlinx_datetimeDateTimeFormatBuilderWithTime>
@required
- (void)dateTimeFormat:(id<SharedKotlinx_datetimeDateTimeFormat>)format __attribute__((swift_name("dateTime(format:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface SharedKotlinByteArray : SharedBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(SharedByte *(^)(SharedInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (SharedKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol SharedKotlinCoroutineContextElement <SharedKotlinCoroutineContext>
@required
@property (readonly) id<SharedKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol SharedKotlinCoroutineContextKey
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestData")))
@interface SharedKtor_client_coreHttpRequestData : SharedBase
- (instancetype)initWithUrl:(SharedKtor_httpUrl *)url method:(SharedKtor_httpHttpMethod *)method headers:(id<SharedKtor_httpHeaders>)headers body:(SharedKtor_httpOutgoingContent *)body executionContext:(id<SharedKotlinx_coroutines_coreJob>)executionContext attributes:(id<SharedKtor_utilsAttributes>)attributes __attribute__((swift_name("init(url:method:headers:body:executionContext:attributes:)"))) __attribute__((objc_designated_initializer));
- (id _Nullable)getCapabilityOrNullKey:(id<SharedKtor_client_coreHttpClientEngineCapability>)key __attribute__((swift_name("getCapabilityOrNull(key:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<SharedKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) SharedKtor_httpOutgoingContent *body __attribute__((swift_name("body")));
@property (readonly) id<SharedKotlinx_coroutines_coreJob> executionContext __attribute__((swift_name("executionContext")));
@property (readonly) id<SharedKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@property (readonly) SharedKtor_httpHttpMethod *method __attribute__((swift_name("method")));
@property (readonly) SharedKtor_httpUrl *url __attribute__((swift_name("url")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponseData")))
@interface SharedKtor_client_coreHttpResponseData : SharedBase
- (instancetype)initWithStatusCode:(SharedKtor_httpHttpStatusCode *)statusCode requestTime:(SharedKtor_utilsGMTDate *)requestTime headers:(id<SharedKtor_httpHeaders>)headers version:(SharedKtor_httpHttpProtocolVersion *)version body:(id)body callContext:(id<SharedKotlinCoroutineContext>)callContext __attribute__((swift_name("init(statusCode:requestTime:headers:version:body:callContext:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id body __attribute__((swift_name("body")));
@property (readonly) id<SharedKotlinCoroutineContext> callContext __attribute__((swift_name("callContext")));
@property (readonly) id<SharedKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@property (readonly) SharedKtor_utilsGMTDate *requestTime __attribute__((swift_name("requestTime")));
@property (readonly) SharedKtor_utilsGMTDate *responseTime __attribute__((swift_name("responseTime")));
@property (readonly) SharedKtor_httpHttpStatusCode *statusCode __attribute__((swift_name("statusCode")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *version __attribute__((swift_name("version")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinAbstractCoroutineContextElement")))
@interface SharedKotlinAbstractCoroutineContextElement : SharedBase <SharedKotlinCoroutineContextElement>
- (instancetype)initWithKey:(id<SharedKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<SharedKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinContinuationInterceptor")))
@protocol SharedKotlinContinuationInterceptor <SharedKotlinCoroutineContextElement>
@required
- (id<SharedKotlinContinuation>)interceptContinuationContinuation:(id<SharedKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (void)releaseInterceptedContinuationContinuation:(id<SharedKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher")))
@interface SharedKotlinx_coroutines_coreCoroutineDispatcher : SharedKotlinAbstractCoroutineContextElement <SharedKotlinContinuationInterceptor>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithKey:(id<SharedKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKotlinx_coroutines_coreCoroutineDispatcherKey *companion __attribute__((swift_name("companion")));
- (void)dispatchContext:(id<SharedKotlinCoroutineContext>)context block:(id<SharedKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatch(context:block:)")));

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (void)dispatchYieldContext:(id<SharedKotlinCoroutineContext>)context block:(id<SharedKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatchYield(context:block:)")));
- (id<SharedKotlinContinuation>)interceptContinuationContinuation:(id<SharedKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (BOOL)isDispatchNeededContext:(id<SharedKotlinCoroutineContext>)context __attribute__((swift_name("isDispatchNeeded(context:)")));
- (SharedKotlinx_coroutines_coreCoroutineDispatcher *)limitedParallelismParallelism:(int32_t)parallelism name:(NSString * _Nullable)name __attribute__((swift_name("limitedParallelism(parallelism:name:)")));
- (SharedKotlinx_coroutines_coreCoroutineDispatcher *)plusOther:(SharedKotlinx_coroutines_coreCoroutineDispatcher *)other __attribute__((swift_name("plus(other:)"))) __attribute__((unavailable("Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.")));
- (void)releaseInterceptedContinuationContinuation:(id<SharedKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreProxyConfig")))
@interface SharedKtor_client_coreProxyConfig : SharedBase
- (instancetype)initWithUrl:(SharedKtor_httpUrl *)url __attribute__((swift_name("init(url:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKtor_httpUrl *url __attribute__((swift_name("url")));
@end

__attribute__((swift_name("Ktor_client_coreHttpClientPlugin")))
@protocol SharedKtor_client_coreHttpClientPlugin
@required
- (void)installPlugin:(id)plugin scope:(SharedKtor_client_coreHttpClient *)scope __attribute__((swift_name("install(plugin:scope:)")));
- (id)prepareBlock:(void (^)(id))block __attribute__((swift_name("prepare(block:)")));
@property (readonly) SharedKtor_utilsAttributeKey<id> *key __attribute__((swift_name("key")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsAttributeKey")))
@interface SharedKtor_utilsAttributeKey<T> : SharedBase

/**
 * @note annotations
 *   kotlin.jvm.JvmOverloads
*/
- (instancetype)initWithName:(NSString *)name type:(SharedKtor_utilsTypeInfo *)type __attribute__((swift_name("init(name:type:)"))) __attribute__((objc_designated_initializer));
- (SharedKtor_utilsAttributeKey<T> *)doCopyName:(NSString *)name type:(SharedKtor_utilsTypeInfo *)type __attribute__((swift_name("doCopy(name:type:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((swift_name("Ktor_eventsEventDefinition")))
@interface SharedKtor_eventsEventDefinition<T> : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreDisposableHandle")))
@protocol SharedKotlinx_coroutines_coreDisposableHandle
@required
- (void)dispose __attribute__((swift_name("dispose()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsPipelinePhase")))
@interface SharedKtor_utilsPipelinePhase : SharedBase
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((swift_name("KotlinFunction")))
@protocol SharedKotlinFunction
@required
@end

__attribute__((swift_name("KotlinSuspendFunction2")))
@protocol SharedKotlinSuspendFunction2 <SharedKotlinFunction>
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeP1:(id _Nullable)p1 p2:(id _Nullable)p2 completionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(p1:p2:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpReceivePipeline.Phases")))
@interface SharedKtor_client_coreHttpReceivePipelinePhases : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_client_coreHttpReceivePipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKtor_utilsPipelinePhase *After __attribute__((swift_name("After")));
@property (readonly) SharedKtor_utilsPipelinePhase *Before __attribute__((swift_name("Before")));
@property (readonly) SharedKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@end

__attribute__((swift_name("Ktor_httpHttpMessage")))
@protocol SharedKtor_httpHttpMessage
@required
@property (readonly) id<SharedKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@end

__attribute__((swift_name("Ktor_client_coreHttpResponse")))
@interface SharedKtor_client_coreHttpResponse : SharedBase <SharedKtor_httpHttpMessage, SharedKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKtor_client_coreHttpClientCall *call __attribute__((swift_name("call")));
@property (readonly) id<SharedKtor_ioByteReadChannel> rawContent __attribute__((swift_name("rawContent")));
@property (readonly) SharedKtor_utilsGMTDate *requestTime __attribute__((swift_name("requestTime")));
@property (readonly) SharedKtor_utilsGMTDate *responseTime __attribute__((swift_name("responseTime")));
@property (readonly) SharedKtor_httpHttpStatusCode *status __attribute__((swift_name("status")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *version_ __attribute__((swift_name("version_")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestPipeline.Phases")))
@interface SharedKtor_client_coreHttpRequestPipelinePhases : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_client_coreHttpRequestPipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKtor_utilsPipelinePhase *Before __attribute__((swift_name("Before")));
@property (readonly) SharedKtor_utilsPipelinePhase *Render __attribute__((swift_name("Render")));
@property (readonly) SharedKtor_utilsPipelinePhase *Send __attribute__((swift_name("Send")));
@property (readonly) SharedKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@property (readonly) SharedKtor_utilsPipelinePhase *Transform __attribute__((swift_name("Transform")));
@end

__attribute__((swift_name("Ktor_httpHttpMessageBuilder")))
@protocol SharedKtor_httpHttpMessageBuilder
@required
@property (readonly) SharedKtor_httpHeadersBuilder *headers __attribute__((swift_name("headers")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestBuilder")))
@interface SharedKtor_client_coreHttpRequestBuilder : SharedBase <SharedKtor_httpHttpMessageBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) SharedKtor_client_coreHttpRequestBuilderCompanion *companion __attribute__((swift_name("companion")));
- (SharedKtor_client_coreHttpRequestData *)build __attribute__((swift_name("build()")));
- (id _Nullable)getCapabilityOrNullKey:(id<SharedKtor_client_coreHttpClientEngineCapability>)key __attribute__((swift_name("getCapabilityOrNull(key:)")));
- (void)setAttributesBlock:(void (^)(id<SharedKtor_utilsAttributes>))block __attribute__((swift_name("setAttributes(block:)")));
- (void)setCapabilityKey:(id<SharedKtor_client_coreHttpClientEngineCapability>)key capability:(id)capability __attribute__((swift_name("setCapability(key:capability:)")));
- (SharedKtor_client_coreHttpRequestBuilder *)takeFromBuilder:(SharedKtor_client_coreHttpRequestBuilder *)builder __attribute__((swift_name("takeFrom(builder:)")));
- (SharedKtor_client_coreHttpRequestBuilder *)takeFromWithExecutionContextBuilder:(SharedKtor_client_coreHttpRequestBuilder *)builder __attribute__((swift_name("takeFromWithExecutionContext(builder:)")));
- (void)urlBlock:(void (^)(SharedKtor_httpURLBuilder *, SharedKtor_httpURLBuilder *))block __attribute__((swift_name("url(block:)")));
@property (readonly) id<SharedKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property id body __attribute__((swift_name("body")));
@property SharedKtor_utilsTypeInfo * _Nullable bodyType __attribute__((swift_name("bodyType")));
@property (readonly) id<SharedKotlinx_coroutines_coreJob> executionContext __attribute__((swift_name("executionContext")));
@property (readonly) SharedKtor_httpHeadersBuilder *headers __attribute__((swift_name("headers")));
@property SharedKtor_httpHttpMethod *method __attribute__((swift_name("method")));
@property (readonly) SharedKtor_httpURLBuilder *url __attribute__((swift_name("url")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponsePipeline.Phases")))
@interface SharedKtor_client_coreHttpResponsePipelinePhases : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_client_coreHttpResponsePipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKtor_utilsPipelinePhase *After __attribute__((swift_name("After")));
@property (readonly) SharedKtor_utilsPipelinePhase *Parse __attribute__((swift_name("Parse")));
@property (readonly) SharedKtor_utilsPipelinePhase *Receive __attribute__((swift_name("Receive")));
@property (readonly) SharedKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@property (readonly) SharedKtor_utilsPipelinePhase *Transform __attribute__((swift_name("Transform")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponseContainer")))
@interface SharedKtor_client_coreHttpResponseContainer : SharedBase
- (instancetype)initWithExpectedType:(SharedKtor_utilsTypeInfo *)expectedType response:(id)response __attribute__((swift_name("init(expectedType:response:)"))) __attribute__((objc_designated_initializer));
- (SharedKtor_client_coreHttpResponseContainer *)doCopyExpectedType:(SharedKtor_utilsTypeInfo *)expectedType response:(id)response __attribute__((swift_name("doCopy(expectedType:response:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKtor_utilsTypeInfo *expectedType __attribute__((swift_name("expectedType")));
@property (readonly) id response __attribute__((swift_name("response")));
@end

__attribute__((swift_name("Ktor_client_coreHttpClientCall")))
@interface SharedKtor_client_coreHttpClientCall : SharedBase <SharedKotlinx_coroutines_coreCoroutineScope>
- (instancetype)initWithClient:(SharedKtor_client_coreHttpClient *)client __attribute__((swift_name("init(client:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithClient:(SharedKtor_client_coreHttpClient *)client requestData:(SharedKtor_client_coreHttpRequestData *)requestData responseData:(SharedKtor_client_coreHttpResponseData *)responseData __attribute__((swift_name("init(client:requestData:responseData:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_client_coreHttpClientCallCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)bodyInfo:(SharedKtor_utilsTypeInfo *)info completionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("body(info:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)bodyNullableInfo:(SharedKtor_utilsTypeInfo *)info completionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("bodyNullable(info:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)getResponseContentWithCompletionHandler:(void (^)(id<SharedKtor_ioByteReadChannel> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getResponseContent(completionHandler:)")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) BOOL allowDoubleReceive __attribute__((swift_name("allowDoubleReceive")));
@property (readonly) id<SharedKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) SharedKtor_client_coreHttpClient *client __attribute__((swift_name("client")));
@property (readonly) id<SharedKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property id<SharedKtor_client_coreHttpRequest> request __attribute__((swift_name("request")));
@property SharedKtor_client_coreHttpResponse *response __attribute__((swift_name("response")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpSendPipeline.Phases")))
@interface SharedKtor_client_coreHttpSendPipelinePhases : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_client_coreHttpSendPipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKtor_utilsPipelinePhase *Before __attribute__((swift_name("Before")));
@property (readonly) SharedKtor_utilsPipelinePhase *Engine __attribute__((swift_name("Engine")));
@property (readonly) SharedKtor_utilsPipelinePhase *Monitoring __attribute__((swift_name("Monitoring")));
@property (readonly) SharedKtor_utilsPipelinePhase *Receive __attribute__((swift_name("Receive")));
@property (readonly) SharedKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol SharedKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<SharedKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<SharedKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<SharedKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) SharedKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface SharedKotlinx_serialization_coreSerializersModule : SharedBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<SharedKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SharedKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<SharedKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<SharedKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SharedKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<SharedKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SharedKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<SharedKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol SharedKotlinAnnotation
@required
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface SharedKotlinx_serialization_coreSerialKind : SharedBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol SharedKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<SharedKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<SharedKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<SharedKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<SharedKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) SharedKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface SharedKotlinNothing : SharedBase
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreBeanDefinition")))
@interface SharedKoin_coreBeanDefinition<T> : SharedBase
- (instancetype)initWithScopeQualifier:(id<SharedKoin_coreQualifier>)scopeQualifier primaryType:(id<SharedKotlinKClass>)primaryType qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier definition:(T _Nullable (^)(SharedKoin_coreScope *, SharedKoin_coreParametersHolder *))definition kind:(SharedKoin_coreKind *)kind secondaryTypes:(NSArray<id<SharedKotlinKClass>> *)secondaryTypes __attribute__((swift_name("init(scopeQualifier:primaryType:qualifier:definition:kind:secondaryTypes:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasTypeClazz:(id<SharedKotlinKClass>)clazz __attribute__((swift_name("hasType(clazz:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isClazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier scopeDefinition:(id<SharedKoin_coreQualifier>)scopeDefinition __attribute__((swift_name("is(clazz:qualifier:scopeDefinition:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property SharedKoin_coreCallbacks<T> *callbacks __attribute__((swift_name("callbacks")));
@property (readonly) T _Nullable (^definition)(SharedKoin_coreScope *, SharedKoin_coreParametersHolder *) __attribute__((swift_name("definition")));
@property (readonly) SharedKoin_coreKind *kind __attribute__((swift_name("kind")));
@property (readonly) id<SharedKotlinKClass> primaryType __attribute__((swift_name("primaryType")));
@property id<SharedKoin_coreQualifier> _Nullable qualifier __attribute__((swift_name("qualifier")));
@property (readonly) id<SharedKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@property NSArray<id<SharedKotlinKClass>> *secondaryTypes __attribute__((swift_name("secondaryTypes")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceFactoryCompanion")))
@interface SharedKoin_coreInstanceFactoryCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKoin_coreInstanceFactoryCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ERROR_SEPARATOR __attribute__((swift_name("ERROR_SEPARATOR")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreResolutionContext")))
@interface SharedKoin_coreResolutionContext : SharedBase
- (instancetype)initWithLogger:(SharedKoin_coreLogger *)logger scope:(SharedKoin_coreScope *)scope clazz:(id<SharedKotlinKClass>)clazz qualifier:(id<SharedKoin_coreQualifier> _Nullable)qualifier parameters:(SharedKoin_coreParametersHolder * _Nullable)parameters __attribute__((swift_name("init(logger:scope:clazz:qualifier:parameters:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<SharedKotlinKClass> clazz __attribute__((swift_name("clazz")));
@property (readonly) NSString *debugTag __attribute__((swift_name("debugTag")));
@property (readonly) SharedKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) SharedKoin_coreParametersHolder * _Nullable parameters __attribute__((swift_name("parameters")));
@property (readonly) id<SharedKoin_coreQualifier> _Nullable qualifier __attribute__((swift_name("qualifier")));
@property (readonly) SharedKoin_coreScope *scope __attribute__((swift_name("scope")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontWeight")))
@interface SharedUi_textFontWeight : SharedBase <SharedKotlinComparable>
- (instancetype)initWithWeight:(int32_t)weight __attribute__((swift_name("init(weight:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textFontWeightCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedUi_textFontWeight *)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t weight __attribute__((swift_name("weight")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_textFontFamily")))
@interface SharedUi_textFontFamily : SharedBase
@property (class, readonly, getter=companion) SharedUi_textFontFamilyCompanion *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL canLoadSynchronously __attribute__((swift_name("canLoadSynchronously")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextGeometricTransform")))
@interface SharedUi_textTextGeometricTransform : SharedBase
- (instancetype)initWithScaleX:(float)scaleX skewX:(float)skewX __attribute__((swift_name("init(scaleX:skewX:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textTextGeometricTransformCompanion *companion __attribute__((swift_name("companion")));
- (SharedUi_textTextGeometricTransform *)doCopyScaleX:(float)scaleX skewX:(float)skewX __attribute__((swift_name("doCopy(scaleX:skewX:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float scaleX __attribute__((swift_name("scaleX")));
@property (readonly) float skewX __attribute__((swift_name("skewX")));
@end

__attribute__((swift_name("KotlinIterable")))
@protocol SharedKotlinIterable
@required
- (id<SharedKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
@end

__attribute__((swift_name("KotlinCollection")))
@protocol SharedKotlinCollection <SharedKotlinIterable>
@required
- (BOOL)containsElement:(id _Nullable)element __attribute__((swift_name("contains(element:)")));
- (BOOL)containsAllElements:(id)elements __attribute__((swift_name("containsAll(elements:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
@property (readonly, getter=size_) int32_t size __attribute__((swift_name("size")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocaleList")))
@interface SharedUi_textLocaleList : SharedBase <SharedKotlinCollection>
- (instancetype)initWithLocales:(SharedKotlinArray<SharedUi_textLocale *> *)locales __attribute__((swift_name("init(locales:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithLanguageTags:(NSString *)languageTags __attribute__((swift_name("init(languageTags:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithLocaleList:(NSArray<SharedUi_textLocale *> *)localeList __attribute__((swift_name("init(localeList:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textLocaleListCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsElement:(SharedUi_textLocale *)element __attribute__((swift_name("contains(element:)")));
- (BOOL)containsAllElements:(id)elements __attribute__((swift_name("containsAll(elements:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (SharedUi_textLocale *)getI:(int32_t)i __attribute__((swift_name("get(i:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
- (id<SharedKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<SharedUi_textLocale *> *localeList __attribute__((swift_name("localeList")));
@property (readonly, getter=size_) int32_t size __attribute__((swift_name("size")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextDecoration")))
@interface SharedUi_textTextDecoration : SharedBase
@property (class, readonly, getter=companion) SharedUi_textTextDecorationCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsOther:(SharedUi_textTextDecoration *)other __attribute__((swift_name("contains(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedUi_textTextDecoration *)plusDecoration:(SharedUi_textTextDecoration *)decoration __attribute__((swift_name("plus(decoration:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t mask __attribute__((swift_name("mask")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsShadow")))
@interface SharedUi_graphicsShadow : SharedBase
- (instancetype)initWithColor:(uint64_t)color offset:(int64_t)offset blurRadius:(float)blurRadius __attribute__((swift_name("init(color:offset:blurRadius:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_graphicsShadowCompanion *companion __attribute__((swift_name("companion")));
- (SharedUi_graphicsShadow *)doCopyColor:(uint64_t)color offset:(int64_t)offset blurRadius:(float)blurRadius __attribute__((swift_name("doCopy(color:offset:blurRadius:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) float blurRadius __attribute__((swift_name("blurRadius")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) uint64_t color __attribute__((swift_name("color")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) int64_t offset __attribute__((swift_name("offset")));
@end

__attribute__((swift_name("Ui_graphicsDrawStyle")))
@interface SharedUi_graphicsDrawStyle : SharedBase
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextIndent")))
@interface SharedUi_textTextIndent : SharedBase
- (instancetype)initWithFirstLine:(int64_t)firstLine restLine:(int64_t)restLine __attribute__((swift_name("init(firstLine:restLine:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textTextIndentCompanion *companion __attribute__((swift_name("companion")));
- (SharedUi_textTextIndent *)doCopyFirstLine:(int64_t)firstLine restLine:(int64_t)restLine __attribute__((swift_name("doCopy(firstLine:restLine:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t firstLine __attribute__((swift_name("firstLine")));
@property (readonly) int64_t restLine __attribute__((swift_name("restLine")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformTextStyle")))
@interface SharedUi_textPlatformTextStyle : SharedBase
- (instancetype)initWithTextDecorationLineStyle:(id _Nullable)textDecorationLineStyle __attribute__((swift_name("init(textDecorationLineStyle:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithSpanStyle:(SharedUi_textPlatformSpanStyle * _Nullable)spanStyle paragraphStyle:(SharedUi_textPlatformParagraphStyle * _Nullable)paragraphStyle __attribute__((swift_name("init(spanStyle:paragraphStyle:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
@property (readonly) SharedUi_textPlatformParagraphStyle * _Nullable paragraphStyle __attribute__((swift_name("paragraphStyle")));
@property (readonly) SharedUi_textPlatformSpanStyle * _Nullable spanStyle __attribute__((swift_name("spanStyle")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLineHeightStyle")))
@interface SharedUi_textLineHeightStyle : SharedBase
- (instancetype)initWithAlignment:(float)alignment trim:(int32_t)trim __attribute__((swift_name("init(alignment:trim:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textLineHeightStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alignment __attribute__((swift_name("alignment")));
@property (readonly) int32_t trim __attribute__((swift_name("trim")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextMotion")))
@interface SharedUi_textTextMotion : SharedBase
@property (class, readonly, getter=companion) SharedUi_textTextMotionCompanion *companion __attribute__((swift_name("companion")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_graphicsBrush")))
@interface SharedUi_graphicsBrush : SharedBase
@property (class, readonly, getter=companion) SharedUi_graphicsBrushCompanion *companion __attribute__((swift_name("companion")));
- (void)applyToSize:(int64_t)size p:(id<SharedUi_graphicsPaint>)p alpha:(float)alpha __attribute__((swift_name("applyTo(size:p:alpha:)")));
@property (readonly) int64_t intrinsicSize __attribute__((swift_name("intrinsicSize")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextStyle.Companion")))
@interface SharedUi_textTextStyleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textTextStyleCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textTextStyle *Default __attribute__((swift_name("Default")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textParagraphStyle")))
@interface SharedUi_textParagraphStyle : SharedBase
- (instancetype)initWithTextAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent platformStyle:(SharedUi_textPlatformParagraphStyle * _Nullable)platformStyle lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("init(textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:textMotion:)"))) __attribute__((objc_designated_initializer));
- (SharedUi_textParagraphStyle *)doCopyTextAlign:(int32_t)textAlign textDirection:(int32_t)textDirection lineHeight:(int64_t)lineHeight textIndent:(SharedUi_textTextIndent * _Nullable)textIndent platformStyle:(SharedUi_textPlatformParagraphStyle * _Nullable)platformStyle lineHeightStyle:(SharedUi_textLineHeightStyle * _Nullable)lineHeightStyle lineBreak:(int32_t)lineBreak hyphens:(int32_t)hyphens textMotion:(SharedUi_textTextMotion * _Nullable)textMotion __attribute__((swift_name("doCopy(textAlign:textDirection:lineHeight:textIndent:platformStyle:lineHeightStyle:lineBreak:hyphens:textMotion:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textParagraphStyle *)mergeOther:(SharedUi_textParagraphStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textParagraphStyle *)plusOther:(SharedUi_textParagraphStyle *)other __attribute__((swift_name("plus(other:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id _Nullable deprecated_boxing_hyphens __attribute__((swift_name("deprecated_boxing_hyphens"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) id _Nullable deprecated_boxing_lineBreak __attribute__((swift_name("deprecated_boxing_lineBreak"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) id _Nullable deprecated_boxing_textAlign __attribute__((swift_name("deprecated_boxing_textAlign"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) id _Nullable deprecated_boxing_textDirection __attribute__((swift_name("deprecated_boxing_textDirection"))) __attribute__((deprecated("Kept for backwards compatibility.")));
@property (readonly) int32_t hyphens __attribute__((swift_name("hyphens")));
@property (readonly) int32_t lineBreak __attribute__((swift_name("lineBreak")));
@property (readonly) int64_t lineHeight __attribute__((swift_name("lineHeight")));
@property (readonly) SharedUi_textLineHeightStyle * _Nullable lineHeightStyle __attribute__((swift_name("lineHeightStyle")));
@property (readonly) SharedUi_textPlatformParagraphStyle * _Nullable platformStyle __attribute__((swift_name("platformStyle")));
@property (readonly) int32_t textAlign __attribute__((swift_name("textAlign")));
@property (readonly) int32_t textDirection __attribute__((swift_name("textDirection")));
@property (readonly) SharedUi_textTextIndent * _Nullable textIndent __attribute__((swift_name("textIndent")));
@property (readonly) SharedUi_textTextMotion * _Nullable textMotion __attribute__((swift_name("textMotion")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textSpanStyle")))
@interface SharedUi_textSpanStyle : SharedBase
- (instancetype)initWithColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow platformStyle:(SharedUi_textPlatformSpanStyle * _Nullable)platformStyle drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle __attribute__((swift_name("init(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:drawStyle:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithBrush:(SharedUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow platformStyle:(SharedUi_textPlatformSpanStyle * _Nullable)platformStyle drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle __attribute__((swift_name("init(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:drawStyle:)"))) __attribute__((objc_designated_initializer));
- (SharedUi_textSpanStyle *)doCopyColor:(uint64_t)color fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow platformStyle:(SharedUi_textPlatformSpanStyle * _Nullable)platformStyle drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle __attribute__((swift_name("doCopy(color:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:drawStyle:)")));
- (SharedUi_textSpanStyle *)doCopyBrush:(SharedUi_graphicsBrush * _Nullable)brush alpha:(float)alpha fontSize:(int64_t)fontSize fontWeight:(SharedUi_textFontWeight * _Nullable)fontWeight fontStyle:(id _Nullable)fontStyle fontSynthesis:(id _Nullable)fontSynthesis fontFamily:(SharedUi_textFontFamily * _Nullable)fontFamily fontFeatureSettings:(NSString * _Nullable)fontFeatureSettings letterSpacing:(int64_t)letterSpacing baselineShift:(id _Nullable)baselineShift textGeometricTransform:(SharedUi_textTextGeometricTransform * _Nullable)textGeometricTransform localeList:(SharedUi_textLocaleList * _Nullable)localeList background:(uint64_t)background textDecoration:(SharedUi_textTextDecoration * _Nullable)textDecoration shadow:(SharedUi_graphicsShadow * _Nullable)shadow platformStyle:(SharedUi_textPlatformSpanStyle * _Nullable)platformStyle drawStyle:(SharedUi_graphicsDrawStyle * _Nullable)drawStyle __attribute__((swift_name("doCopy(brush:alpha:fontSize:fontWeight:fontStyle:fontSynthesis:fontFamily:fontFeatureSettings:letterSpacing:baselineShift:textGeometricTransform:localeList:background:textDecoration:shadow:platformStyle:drawStyle:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textSpanStyle *)mergeOther:(SharedUi_textSpanStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_textSpanStyle *)plusOther:(SharedUi_textSpanStyle *)other __attribute__((swift_name("plus(other:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float alpha __attribute__((swift_name("alpha")));
@property (readonly) uint64_t background __attribute__((swift_name("background")));
@property (readonly) id _Nullable baselineShift __attribute__((swift_name("baselineShift")));
@property (readonly) SharedUi_graphicsBrush * _Nullable brush __attribute__((swift_name("brush")));
@property (readonly) uint64_t color __attribute__((swift_name("color")));
@property (readonly) SharedUi_graphicsDrawStyle * _Nullable drawStyle __attribute__((swift_name("drawStyle")));
@property (readonly) SharedUi_textFontFamily * _Nullable fontFamily __attribute__((swift_name("fontFamily")));
@property (readonly) NSString * _Nullable fontFeatureSettings __attribute__((swift_name("fontFeatureSettings")));
@property (readonly) int64_t fontSize __attribute__((swift_name("fontSize")));
@property (readonly) id _Nullable fontStyle __attribute__((swift_name("fontStyle")));
@property (readonly) id _Nullable fontSynthesis __attribute__((swift_name("fontSynthesis")));
@property (readonly) SharedUi_textFontWeight * _Nullable fontWeight __attribute__((swift_name("fontWeight")));
@property (readonly) int64_t letterSpacing __attribute__((swift_name("letterSpacing")));
@property (readonly) SharedUi_textLocaleList * _Nullable localeList __attribute__((swift_name("localeList")));
@property (readonly) SharedUi_textPlatformSpanStyle * _Nullable platformStyle __attribute__((swift_name("platformStyle")));
@property (readonly) SharedUi_graphicsShadow * _Nullable shadow __attribute__((swift_name("shadow")));
@property (readonly) SharedUi_textTextDecoration * _Nullable textDecoration __attribute__((swift_name("textDecoration")));
@property (readonly) SharedUi_textTextGeometricTransform * _Nullable textGeometricTransform __attribute__((swift_name("textGeometricTransform")));
@end

__attribute__((swift_name("KotlinAppendable")))
@protocol SharedKotlinAppendable
@required
- (id<SharedKotlinAppendable>)appendValue:(unichar)value __attribute__((swift_name("append(value:)")));
- (id<SharedKotlinAppendable>)appendValue_:(id _Nullable)value __attribute__((swift_name("append(value_:)")));
- (id<SharedKotlinAppendable>)appendValue:(id _Nullable)value startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("append(value:startIndex:endIndex:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimePadding")))
@interface SharedKotlinx_datetimePadding : SharedKotlinEnum<SharedKotlinx_datetimePadding *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKotlinx_datetimePadding *none __attribute__((swift_name("none")));
@property (class, readonly) SharedKotlinx_datetimePadding *zero __attribute__((swift_name("zero")));
@property (class, readonly) SharedKotlinx_datetimePadding *space __attribute__((swift_name("space")));
+ (SharedKotlinArray<SharedKotlinx_datetimePadding *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKotlinx_datetimePadding *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeDayOfWeekNames")))
@interface SharedKotlinx_datetimeDayOfWeekNames : SharedBase
- (instancetype)initWithNames:(NSArray<NSString *> *)names __attribute__((swift_name("init(names:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMonday:(NSString *)monday tuesday:(NSString *)tuesday wednesday:(NSString *)wednesday thursday:(NSString *)thursday friday:(NSString *)friday saturday:(NSString *)saturday sunday:(NSString *)sunday __attribute__((swift_name("init(monday:tuesday:wednesday:thursday:friday:saturday:sunday:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinx_datetimeDayOfWeekNamesCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<NSString *> *names __attribute__((swift_name("names")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeMonthNames")))
@interface SharedKotlinx_datetimeMonthNames : SharedBase
- (instancetype)initWithNames:(NSArray<NSString *> *)names __attribute__((swift_name("init(names:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithJanuary:(NSString *)january february:(NSString *)february march:(NSString *)march april:(NSString *)april may:(NSString *)may june:(NSString *)june july:(NSString *)july august:(NSString *)august september:(NSString *)september october:(NSString *)october november:(NSString *)november december:(NSString *)december __attribute__((swift_name("init(january:february:march:april:may:june:july:august:september:october:november:december:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinx_datetimeMonthNamesCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<NSString *> *names __attribute__((swift_name("names")));
@end

__attribute__((swift_name("KotlinByteIterator")))
@interface SharedKotlinByteIterator : SharedBase <SharedKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (SharedByte *)next __attribute__((swift_name("next()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpUrl")))
@interface SharedKtor_httpUrl : SharedBase
@property (class, readonly, getter=companion) SharedKtor_httpUrlCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *encodedFragment __attribute__((swift_name("encodedFragment")));
@property (readonly) NSString * _Nullable encodedPassword __attribute__((swift_name("encodedPassword")));
@property (readonly) NSString *encodedPath __attribute__((swift_name("encodedPath")));
@property (readonly) NSString *encodedPathAndQuery __attribute__((swift_name("encodedPathAndQuery")));
@property (readonly) NSString *encodedQuery __attribute__((swift_name("encodedQuery")));
@property (readonly) NSString * _Nullable encodedUser __attribute__((swift_name("encodedUser")));
@property (readonly) NSString *fragment __attribute__((swift_name("fragment")));
@property (readonly) NSString *host __attribute__((swift_name("host")));
@property (readonly) id<SharedKtor_httpParameters> parameters __attribute__((swift_name("parameters")));
@property (readonly) NSString * _Nullable password __attribute__((swift_name("password")));
@property (readonly) NSArray<NSString *> *pathSegments __attribute__((swift_name("pathSegments"))) __attribute__((deprecated("\n        `pathSegments` is deprecated.\n\n        This property will contain an empty path segment at the beginning for URLs with a hostname,\n        and an empty path segment at the end for the URLs with a trailing slash. If you need to keep this behaviour please\n        use [rawSegments]. If you only need to access the meaningful parts of the path, consider using [segments] instead.\n             \n        Please decide if you need [rawSegments] or [segments] explicitly.\n        ")));
@property (readonly) int32_t port __attribute__((swift_name("port")));
@property (readonly) SharedKtor_httpURLProtocol *protocol __attribute__((swift_name("protocol")));
@property (readonly) SharedKtor_httpURLProtocol * _Nullable protocolOrNull __attribute__((swift_name("protocolOrNull")));
@property (readonly) NSArray<NSString *> *rawSegments __attribute__((swift_name("rawSegments")));
@property (readonly) NSArray<NSString *> *segments __attribute__((swift_name("segments")));
@property (readonly) int32_t specifiedPort __attribute__((swift_name("specifiedPort")));
@property (readonly) BOOL trailingQuery __attribute__((swift_name("trailingQuery")));
@property (readonly) NSString * _Nullable user __attribute__((swift_name("user")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpMethod")))
@interface SharedKtor_httpHttpMethod : SharedBase
- (instancetype)initWithValue:(NSString *)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_httpHttpMethodCompanion *companion __attribute__((swift_name("companion")));
- (SharedKtor_httpHttpMethod *)doCopyValue:(NSString *)value __attribute__((swift_name("doCopy(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("Ktor_utilsStringValues")))
@protocol SharedKtor_utilsStringValues
@required
- (BOOL)containsName:(NSString *)name __attribute__((swift_name("contains(name:)")));
- (BOOL)containsName:(NSString *)name value:(NSString *)value __attribute__((swift_name("contains(name:value:)")));
- (NSSet<id<SharedKotlinMapEntry>> *)entries __attribute__((swift_name("entries()")));
- (void)forEachBody:(void (^)(NSString *, NSArray<NSString *> *))body __attribute__((swift_name("forEach(body:)")));
- (NSString * _Nullable)getName:(NSString *)name __attribute__((swift_name("get(name:)")));
- (NSArray<NSString *> * _Nullable)getAllName:(NSString *)name __attribute__((swift_name("getAll(name:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
- (NSSet<NSString *> *)names __attribute__((swift_name("names()")));
@property (readonly) BOOL caseInsensitiveName __attribute__((swift_name("caseInsensitiveName")));
@end

__attribute__((swift_name("Ktor_httpHeaders")))
@protocol SharedKtor_httpHeaders <SharedKtor_utilsStringValues>
@required
@end

__attribute__((swift_name("Ktor_httpOutgoingContent")))
@interface SharedKtor_httpOutgoingContent : SharedBase
- (id _Nullable)getPropertyKey:(SharedKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("getProperty(key:)")));
- (void)setPropertyKey:(SharedKtor_utilsAttributeKey<id> *)key value:(id _Nullable)value __attribute__((swift_name("setProperty(key:value:)")));
- (id<SharedKtor_httpHeaders> _Nullable)trailers __attribute__((swift_name("trailers()")));
@property (readonly) SharedLong * _Nullable contentLength __attribute__((swift_name("contentLength")));
@property (readonly) SharedKtor_httpContentType * _Nullable contentType __attribute__((swift_name("contentType")));
@property (readonly) id<SharedKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@property (readonly) SharedKtor_httpHttpStatusCode * _Nullable status __attribute__((swift_name("status")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreJob")))
@protocol SharedKotlinx_coroutines_coreJob <SharedKotlinCoroutineContextElement>
@required

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (id<SharedKotlinx_coroutines_coreChildHandle>)attachChildChild:(id<SharedKotlinx_coroutines_coreChildJob>)child __attribute__((swift_name("attachChild(child:)")));
- (void)cancelCause:(SharedKotlinCancellationException * _Nullable)cause __attribute__((swift_name("cancel(cause:)")));

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (SharedKotlinCancellationException *)getCancellationException __attribute__((swift_name("getCancellationException()")));
- (id<SharedKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionHandler:(void (^)(SharedKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(handler:)")));

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (id<SharedKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionOnCancelling:(BOOL)onCancelling invokeImmediately:(BOOL)invokeImmediately handler:(void (^)(SharedKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(onCancelling:invokeImmediately:handler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("join(completionHandler:)")));
- (id<SharedKotlinx_coroutines_coreJob>)plusOther_:(id<SharedKotlinx_coroutines_coreJob>)other __attribute__((swift_name("plus(other_:)"))) __attribute__((unavailable("Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")));
- (BOOL)start __attribute__((swift_name("start()")));
@property (readonly) id<SharedKotlinSequence> children __attribute__((swift_name("children")));
@property (readonly) BOOL isActive __attribute__((swift_name("isActive")));
@property (readonly) BOOL isCancelled __attribute__((swift_name("isCancelled")));
@property (readonly) BOOL isCompleted __attribute__((swift_name("isCompleted")));
@property (readonly) id<SharedKotlinx_coroutines_coreSelectClause0> onJoin __attribute__((swift_name("onJoin")));

/**
 * @note annotations
 *   kotlinx.coroutines.ExperimentalCoroutinesApi
*/
@property (readonly) id<SharedKotlinx_coroutines_coreJob> _Nullable parent __attribute__((swift_name("parent")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpStatusCode")))
@interface SharedKtor_httpHttpStatusCode : SharedBase <SharedKotlinComparable>
- (instancetype)initWithValue:(int32_t)value description:(NSString *)description __attribute__((swift_name("init(value:description:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_httpHttpStatusCodeCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedKtor_httpHttpStatusCode *)other __attribute__((swift_name("compareTo(other:)")));
- (SharedKtor_httpHttpStatusCode *)doCopyValue:(int32_t)value description:(NSString *)description __attribute__((swift_name("doCopy(value:description:)")));
- (SharedKtor_httpHttpStatusCode *)descriptionValue:(NSString *)value __attribute__((swift_name("description(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *description_ __attribute__((swift_name("description_")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsGMTDate")))
@interface SharedKtor_utilsGMTDate : SharedBase <SharedKotlinComparable>
- (instancetype)initWithSeconds:(int32_t)seconds minutes:(int32_t)minutes hours:(int32_t)hours dayOfWeek:(SharedKtor_utilsWeekDay *)dayOfWeek dayOfMonth:(int32_t)dayOfMonth dayOfYear:(int32_t)dayOfYear month:(SharedKtor_utilsMonth *)month year:(int32_t)year timestamp:(int64_t)timestamp __attribute__((swift_name("init(seconds:minutes:hours:dayOfWeek:dayOfMonth:dayOfYear:month:year:timestamp:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_utilsGMTDateCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(SharedKtor_utilsGMTDate *)other __attribute__((swift_name("compareTo(other:)")));
- (SharedKtor_utilsGMTDate *)doCopy __attribute__((swift_name("doCopy()")));
- (SharedKtor_utilsGMTDate *)doCopySeconds:(int32_t)seconds minutes:(int32_t)minutes hours:(int32_t)hours dayOfWeek:(SharedKtor_utilsWeekDay *)dayOfWeek dayOfMonth:(int32_t)dayOfMonth dayOfYear:(int32_t)dayOfYear month:(SharedKtor_utilsMonth *)month year:(int32_t)year timestamp:(int64_t)timestamp __attribute__((swift_name("doCopy(seconds:minutes:hours:dayOfWeek:dayOfMonth:dayOfYear:month:year:timestamp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t dayOfMonth __attribute__((swift_name("dayOfMonth")));
@property (readonly) SharedKtor_utilsWeekDay *dayOfWeek __attribute__((swift_name("dayOfWeek")));
@property (readonly) int32_t dayOfYear __attribute__((swift_name("dayOfYear")));
@property (readonly) int32_t hours __attribute__((swift_name("hours")));
@property (readonly) int32_t minutes __attribute__((swift_name("minutes")));
@property (readonly) SharedKtor_utilsMonth *month __attribute__((swift_name("month")));
@property (readonly) int32_t seconds __attribute__((swift_name("seconds")));
@property (readonly) int64_t timestamp __attribute__((swift_name("timestamp")));
@property (readonly) int32_t year __attribute__((swift_name("year")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpProtocolVersion")))
@interface SharedKtor_httpHttpProtocolVersion : SharedBase
- (instancetype)initWithName:(NSString *)name major:(int32_t)major minor:(int32_t)minor __attribute__((swift_name("init(name:major:minor:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_httpHttpProtocolVersionCompanion *companion __attribute__((swift_name("companion")));
- (SharedKtor_httpHttpProtocolVersion *)doCopyName:(NSString *)name major:(int32_t)major minor:(int32_t)minor __attribute__((swift_name("doCopy(name:major:minor:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t major __attribute__((swift_name("major")));
@property (readonly) int32_t minor __attribute__((swift_name("minor")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinContinuation")))
@protocol SharedKotlinContinuation
@required
- (void)resumeWithResult:(id _Nullable)result __attribute__((swift_name("resumeWith(result:)")));
@property (readonly) id<SharedKotlinCoroutineContext> context __attribute__((swift_name("context")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
 *   kotlin.ExperimentalStdlibApi
*/
__attribute__((swift_name("KotlinAbstractCoroutineContextKey")))
@interface SharedKotlinAbstractCoroutineContextKey<B, E> : SharedBase <SharedKotlinCoroutineContextKey>
- (instancetype)initWithBaseKey:(id<SharedKotlinCoroutineContextKey>)baseKey safeCast:(E _Nullable (^)(id<SharedKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.ExperimentalStdlibApi
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher.Key")))
@interface SharedKotlinx_coroutines_coreCoroutineDispatcherKey : SharedKotlinAbstractCoroutineContextKey<id<SharedKotlinContinuationInterceptor>, SharedKotlinx_coroutines_coreCoroutineDispatcher *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithBaseKey:(id<SharedKotlinCoroutineContextKey>)baseKey safeCast:(id<SharedKotlinCoroutineContextElement> _Nullable (^)(id<SharedKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)key __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_coroutines_coreCoroutineDispatcherKey *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreRunnable")))
@protocol SharedKotlinx_coroutines_coreRunnable
@required
- (void)run __attribute__((swift_name("run()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsTypeInfo")))
@interface SharedKtor_utilsTypeInfo : SharedBase
- (instancetype)initWithType:(id<SharedKotlinKClass>)type kotlinType:(id<SharedKotlinKType> _Nullable)kotlinType __attribute__((swift_name("init(type:kotlinType:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithType:(id<SharedKotlinKClass>)type reifiedType:(id<SharedKotlinKType>)reifiedType kotlinType:(id<SharedKotlinKType> _Nullable)kotlinType __attribute__((swift_name("init(type:reifiedType:kotlinType:)"))) __attribute__((objc_designated_initializer)) __attribute__((deprecated("Use constructor without reifiedType parameter.")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<SharedKotlinKType> _Nullable kotlinType __attribute__((swift_name("kotlinType")));
@property (readonly) id<SharedKotlinKClass> type __attribute__((swift_name("type")));
@end

__attribute__((swift_name("Ktor_ioByteReadChannel")))
@protocol SharedKtor_ioByteReadChannel
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)awaitContentMin:(int32_t)min completionHandler:(void (^)(SharedBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("awaitContent(min:completionHandler:)")));
- (void)cancelCause_:(SharedKotlinThrowable * _Nullable)cause __attribute__((swift_name("cancel(cause_:)")));
@property (readonly) SharedKotlinThrowable * _Nullable closedCause __attribute__((swift_name("closedCause")));
@property (readonly) BOOL isClosedForRead __attribute__((swift_name("isClosedForRead")));
@property (readonly) id<SharedKotlinx_io_coreSource> readBuffer __attribute__((swift_name("readBuffer")));
@end

__attribute__((swift_name("Ktor_utilsStringValuesBuilder")))
@protocol SharedKtor_utilsStringValuesBuilder
@required
- (void)appendName:(NSString *)name value:(NSString *)value __attribute__((swift_name("append(name:value:)")));
- (void)appendAllStringValues:(id<SharedKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendAll(stringValues:)")));
- (void)appendAllName:(NSString *)name values:(id)values __attribute__((swift_name("appendAll(name:values:)")));
- (void)appendMissingStringValues:(id<SharedKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendMissing(stringValues:)")));
- (void)appendMissingName:(NSString *)name values:(id)values __attribute__((swift_name("appendMissing(name:values:)")));
- (id<SharedKtor_utilsStringValues>)build __attribute__((swift_name("build()")));
- (void)clear __attribute__((swift_name("clear()")));
- (BOOL)containsName:(NSString *)name __attribute__((swift_name("contains(name:)")));
- (BOOL)containsName:(NSString *)name value:(NSString *)value __attribute__((swift_name("contains(name:value:)")));
- (NSSet<id<SharedKotlinMapEntry>> *)entries __attribute__((swift_name("entries()")));
- (NSString * _Nullable)getName:(NSString *)name __attribute__((swift_name("get(name:)")));
- (NSArray<NSString *> * _Nullable)getAllName:(NSString *)name __attribute__((swift_name("getAll(name:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
- (NSSet<NSString *> *)names __attribute__((swift_name("names()")));
- (void)removeName:(NSString *)name __attribute__((swift_name("remove(name:)")));
- (BOOL)removeName:(NSString *)name value:(NSString *)value __attribute__((swift_name("remove(name:value:)")));
- (void)removeKeysWithNoEntries __attribute__((swift_name("removeKeysWithNoEntries()")));
- (void)setName:(NSString *)name value:(NSString *)value __attribute__((swift_name("set(name:value:)")));
@property (readonly) BOOL caseInsensitiveName __attribute__((swift_name("caseInsensitiveName")));
@end

__attribute__((swift_name("Ktor_utilsStringValuesBuilderImpl")))
@interface SharedKtor_utilsStringValuesBuilderImpl : SharedBase <SharedKtor_utilsStringValuesBuilder>
- (instancetype)initWithCaseInsensitiveName:(BOOL)caseInsensitiveName size:(int32_t)size __attribute__((swift_name("init(caseInsensitiveName:size:)"))) __attribute__((objc_designated_initializer));
- (void)appendName:(NSString *)name value:(NSString *)value __attribute__((swift_name("append(name:value:)")));
- (void)appendAllStringValues:(id<SharedKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendAll(stringValues:)")));
- (void)appendAllName:(NSString *)name values:(id)values __attribute__((swift_name("appendAll(name:values:)")));
- (void)appendMissingStringValues:(id<SharedKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendMissing(stringValues:)")));
- (void)appendMissingName:(NSString *)name values:(id)values __attribute__((swift_name("appendMissing(name:values:)")));
- (id<SharedKtor_utilsStringValues>)build __attribute__((swift_name("build()")));
- (void)clear __attribute__((swift_name("clear()")));
- (BOOL)containsName:(NSString *)name __attribute__((swift_name("contains(name:)")));
- (BOOL)containsName:(NSString *)name value:(NSString *)value __attribute__((swift_name("contains(name:value:)")));
- (NSSet<id<SharedKotlinMapEntry>> *)entries __attribute__((swift_name("entries()")));
- (NSString * _Nullable)getName:(NSString *)name __attribute__((swift_name("get(name:)")));
- (NSArray<NSString *> * _Nullable)getAllName:(NSString *)name __attribute__((swift_name("getAll(name:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
- (NSSet<NSString *> *)names __attribute__((swift_name("names()")));
- (void)removeName:(NSString *)name __attribute__((swift_name("remove(name:)")));
- (BOOL)removeName:(NSString *)name value:(NSString *)value __attribute__((swift_name("remove(name:value:)")));
- (void)removeKeysWithNoEntries __attribute__((swift_name("removeKeysWithNoEntries()")));
- (void)setName:(NSString *)name value:(NSString *)value __attribute__((swift_name("set(name:value:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)validateNameName:(NSString *)name __attribute__((swift_name("validateName(name:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)validateValueValue:(NSString *)value __attribute__((swift_name("validateValue(value:)")));
@property (readonly) BOOL caseInsensitiveName __attribute__((swift_name("caseInsensitiveName")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) SharedMutableDictionary<NSString *, NSMutableArray<NSString *> *> *values __attribute__((swift_name("values")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHeadersBuilder")))
@interface SharedKtor_httpHeadersBuilder : SharedKtor_utilsStringValuesBuilderImpl
- (instancetype)initWithSize:(int32_t)size __attribute__((swift_name("init(size:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCaseInsensitiveName:(BOOL)caseInsensitiveName size:(int32_t)size __attribute__((swift_name("init(caseInsensitiveName:size:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (id<SharedKtor_httpHeaders>)build __attribute__((swift_name("build()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)validateNameName:(NSString *)name __attribute__((swift_name("validateName(name:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)validateValueValue:(NSString *)value __attribute__((swift_name("validateValue(value:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestBuilder.Companion")))
@interface SharedKtor_client_coreHttpRequestBuilderCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_client_coreHttpRequestBuilderCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLBuilder")))
@interface SharedKtor_httpURLBuilder : SharedBase
- (instancetype)initWithProtocol:(SharedKtor_httpURLProtocol * _Nullable)protocol host:(NSString *)host port:(int32_t)port user:(NSString * _Nullable)user password:(NSString * _Nullable)password pathSegments:(NSArray<NSString *> *)pathSegments parameters:(id<SharedKtor_httpParameters>)parameters fragment:(NSString *)fragment trailingQuery:(BOOL)trailingQuery __attribute__((swift_name("init(protocol:host:port:user:password:pathSegments:parameters:fragment:trailingQuery:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_httpURLBuilderCompanion *companion __attribute__((swift_name("companion")));
- (SharedKtor_httpUrl *)build __attribute__((swift_name("build()")));
- (NSString *)buildString __attribute__((swift_name("buildString()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSString *encodedFragment __attribute__((swift_name("encodedFragment")));
@property id<SharedKtor_httpParametersBuilder> encodedParameters __attribute__((swift_name("encodedParameters")));
@property NSString * _Nullable encodedPassword __attribute__((swift_name("encodedPassword")));
@property NSArray<NSString *> *encodedPathSegments __attribute__((swift_name("encodedPathSegments")));
@property NSString * _Nullable encodedUser __attribute__((swift_name("encodedUser")));
@property NSString *fragment __attribute__((swift_name("fragment")));
@property NSString *host __attribute__((swift_name("host")));
@property (readonly) id<SharedKtor_httpParametersBuilder> parameters __attribute__((swift_name("parameters")));
@property NSString * _Nullable password __attribute__((swift_name("password")));
@property NSArray<NSString *> *pathSegments __attribute__((swift_name("pathSegments")));
@property int32_t port __attribute__((swift_name("port")));
@property SharedKtor_httpURLProtocol *protocol __attribute__((swift_name("protocol")));
@property SharedKtor_httpURLProtocol * _Nullable protocolOrNull __attribute__((swift_name("protocolOrNull")));
@property BOOL trailingQuery __attribute__((swift_name("trailingQuery")));
@property NSString * _Nullable user __attribute__((swift_name("user")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpClientCall.Companion")))
@interface SharedKtor_client_coreHttpClientCallCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_client_coreHttpClientCallCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Ktor_client_coreHttpRequest")))
@protocol SharedKtor_client_coreHttpRequest <SharedKtor_httpHttpMessage, SharedKotlinx_coroutines_coreCoroutineScope>
@required
@property (readonly) id<SharedKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) SharedKtor_client_coreHttpClientCall *call __attribute__((swift_name("call")));
@property (readonly) SharedKtor_httpOutgoingContent *content __attribute__((swift_name("content")));
@property (readonly) SharedKtor_httpHttpMethod *method __attribute__((swift_name("method")));
@property (readonly) SharedKtor_httpUrl *url __attribute__((swift_name("url")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol SharedKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<SharedKotlinKClass>)kClass provider:(id<SharedKotlinx_serialization_coreKSerializer> (^)(NSArray<id<SharedKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<SharedKotlinKClass>)kClass serializer:(id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<SharedKotlinKClass>)baseClass actualClass:(id<SharedKotlinKClass>)actualClass actualSerializer:(id<SharedKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<SharedKotlinKClass>)baseClass defaultDeserializerProvider:(id<SharedKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<SharedKotlinKClass>)baseClass defaultDeserializerProvider:(id<SharedKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<SharedKotlinKClass>)baseClass defaultSerializerProvider:(id<SharedKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKind")))
@interface SharedKoin_coreKind : SharedKotlinEnum<SharedKoin_coreKind *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKoin_coreKind *singleton __attribute__((swift_name("singleton")));
@property (class, readonly) SharedKoin_coreKind *factory __attribute__((swift_name("factory")));
@property (class, readonly) SharedKoin_coreKind *scoped __attribute__((swift_name("scoped")));
+ (SharedKotlinArray<SharedKoin_coreKind *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKoin_coreKind *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreCallbacks")))
@interface SharedKoin_coreCallbacks<T> : SharedBase
- (instancetype)initWithOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("init(onClose:)"))) __attribute__((objc_designated_initializer));
- (SharedKoin_coreCallbacks<T> *)doCopyOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("doCopy(onClose:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^ _Nullable onClose)(T _Nullable) __attribute__((swift_name("onClose")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontWeight.Companion")))
@interface SharedUi_textFontWeightCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textFontWeightCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *Black __attribute__((swift_name("Black")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *Bold __attribute__((swift_name("Bold")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *ExtraBold __attribute__((swift_name("ExtraBold")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *ExtraLight __attribute__((swift_name("ExtraLight")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *Light __attribute__((swift_name("Light")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *Medium __attribute__((swift_name("Medium")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *Normal __attribute__((swift_name("Normal")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *SemiBold __attribute__((swift_name("SemiBold")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *Thin __attribute__((swift_name("Thin")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W100 __attribute__((swift_name("W100")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W200 __attribute__((swift_name("W200")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W300 __attribute__((swift_name("W300")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W400 __attribute__((swift_name("W400")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W500 __attribute__((swift_name("W500")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W600 __attribute__((swift_name("W600")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W700 __attribute__((swift_name("W700")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W800 __attribute__((swift_name("W800")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textFontWeight *W900 __attribute__((swift_name("W900")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontFamily.Companion")))
@interface SharedUi_textFontFamilyCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textFontFamilyCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textGenericFontFamily *Cursive __attribute__((swift_name("Cursive")));
@property (readonly) SharedUi_textSystemFontFamily *Default __attribute__((swift_name("Default")));
@property (readonly) SharedUi_textGenericFontFamily *Monospace __attribute__((swift_name("Monospace")));
@property (readonly) SharedUi_textGenericFontFamily *SansSerif __attribute__((swift_name("SansSerif")));
@property (readonly) SharedUi_textGenericFontFamily *Serif __attribute__((swift_name("Serif")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextGeometricTransform.Companion")))
@interface SharedUi_textTextGeometricTransformCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textTextGeometricTransformCompanion *shared __attribute__((swift_name("shared")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocale")))
@interface SharedUi_textLocale : SharedBase
- (instancetype)initWithLanguageTag:(NSString *)languageTag __attribute__((swift_name("init(languageTag:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textLocaleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)toLanguageTag __attribute__((swift_name("toLanguageTag()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *language __attribute__((swift_name("language")));
@property (readonly) NSLocale *platformLocale __attribute__((swift_name("platformLocale")));
@property (readonly) NSString *region __attribute__((swift_name("region")));
@property (readonly) NSString *script __attribute__((swift_name("script")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocaleList.Companion")))
@interface SharedUi_textLocaleListCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textLocaleListCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textLocaleList *Empty __attribute__((swift_name("Empty")));
@property (readonly) SharedUi_textLocaleList *current __attribute__((swift_name("current")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextDecoration.Companion")))
@interface SharedUi_textTextDecorationCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textTextDecorationCompanion *shared __attribute__((swift_name("shared")));
- (SharedUi_textTextDecoration *)combineDecorations:(NSArray<SharedUi_textTextDecoration *> *)decorations __attribute__((swift_name("combine(decorations:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textTextDecoration *LineThrough __attribute__((swift_name("LineThrough")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textTextDecoration *None __attribute__((swift_name("None")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textTextDecoration *Underline __attribute__((swift_name("Underline")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsShadow.Companion")))
@interface SharedUi_graphicsShadowCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_graphicsShadowCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_graphicsShadow *None __attribute__((swift_name("None")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextIndent.Companion")))
@interface SharedUi_textTextIndentCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textTextIndentCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
@property (readonly) SharedUi_textTextIndent *None __attribute__((swift_name("None")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformSpanStyle")))
@interface SharedUi_textPlatformSpanStyle : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithTextDecorationLineStyle:(id _Nullable)textDecorationLineStyle __attribute__((swift_name("init(textDecorationLineStyle:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textPlatformSpanStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedUi_textPlatformSpanStyle *)mergeOther:(SharedUi_textPlatformSpanStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));
@property (readonly) id _Nullable textDecorationLineStyle __attribute__((swift_name("textDecorationLineStyle")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformParagraphStyle")))
@interface SharedUi_textPlatformParagraphStyle : SharedBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithFontRasterizationSettings:(SharedUi_textFontRasterizationSettings *)fontRasterizationSettings __attribute__((swift_name("init(fontRasterizationSettings:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textPlatformParagraphStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedUi_textPlatformParagraphStyle *)mergeOther:(SharedUi_textPlatformParagraphStyle * _Nullable)other __attribute__((swift_name("merge(other:)")));
@property (readonly) SharedUi_textFontRasterizationSettings *fontRasterizationSettings __attribute__((swift_name("fontRasterizationSettings")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLineHeightStyle.Companion")))
@interface SharedUi_textLineHeightStyleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textLineHeightStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textLineHeightStyle *Default __attribute__((swift_name("Default")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textTextMotion.Companion")))
@interface SharedUi_textTextMotionCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textTextMotionCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textTextMotion *Animated __attribute__((swift_name("Animated")));
@property (readonly) SharedUi_textTextMotion *Static __attribute__((swift_name("Static")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsBrush.Companion")))
@interface SharedUi_graphicsBrushCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_graphicsBrushCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)horizontalGradientColorStops:(SharedKotlinArray<SharedKotlinPair<SharedFloat *, id> *> *)colorStops startX:(float)startX endX:(float)endX tileMode:(int32_t)tileMode __attribute__((swift_name("horizontalGradient(colorStops:startX:endX:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)horizontalGradientColors:(NSArray<id> *)colors startX:(float)startX endX:(float)endX tileMode:(int32_t)tileMode __attribute__((swift_name("horizontalGradient(colors:startX:endX:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)linearGradientColorStops:(SharedKotlinArray<SharedKotlinPair<SharedFloat *, id> *> *)colorStops start:(int64_t)start end:(int64_t)end tileMode:(int32_t)tileMode __attribute__((swift_name("linearGradient(colorStops:start:end:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)linearGradientColors:(NSArray<id> *)colors start:(int64_t)start end:(int64_t)end tileMode:(int32_t)tileMode __attribute__((swift_name("linearGradient(colors:start:end:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)radialGradientColorStops:(SharedKotlinArray<SharedKotlinPair<SharedFloat *, id> *> *)colorStops center:(int64_t)center radius:(float)radius tileMode:(int32_t)tileMode __attribute__((swift_name("radialGradient(colorStops:center:radius:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)radialGradientColors:(NSArray<id> *)colors center:(int64_t)center radius:(float)radius tileMode:(int32_t)tileMode __attribute__((swift_name("radialGradient(colors:center:radius:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)sweepGradientColorStops:(SharedKotlinArray<SharedKotlinPair<SharedFloat *, id> *> *)colorStops center:(int64_t)center __attribute__((swift_name("sweepGradient(colorStops:center:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)sweepGradientColors:(NSArray<id> *)colors center:(int64_t)center __attribute__((swift_name("sweepGradient(colors:center:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)verticalGradientColorStops:(SharedKotlinArray<SharedKotlinPair<SharedFloat *, id> *> *)colorStops startY:(float)startY endY:(float)endY tileMode:(int32_t)tileMode __attribute__((swift_name("verticalGradient(colorStops:startY:endY:tileMode:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsBrush *)verticalGradientColors:(NSArray<id> *)colors startY:(float)startY endY:(float)endY tileMode:(int32_t)tileMode __attribute__((swift_name("verticalGradient(colors:startY:endY:tileMode:)")));
@end

__attribute__((swift_name("Ui_graphicsPaint")))
@protocol SharedUi_graphicsPaint
@required
- (SharedSkikoPaint *)asFrameworkPaint __attribute__((swift_name("asFrameworkPaint()")));
@property float alpha __attribute__((swift_name("alpha")));
@property int32_t blendMode __attribute__((swift_name("blendMode")));
@property uint64_t color __attribute__((swift_name("color")));
@property SharedUi_graphicsColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property int32_t filterQuality __attribute__((swift_name("filterQuality")));
@property BOOL isAntiAlias __attribute__((swift_name("isAntiAlias")));
@property id<SharedUi_graphicsPathEffect> _Nullable pathEffect __attribute__((swift_name("pathEffect")));
@property SharedSkikoShader * _Nullable shader __attribute__((swift_name("shader")));
@property int32_t strokeCap __attribute__((swift_name("strokeCap")));
@property int32_t strokeJoin __attribute__((swift_name("strokeJoin")));
@property float strokeMiterLimit __attribute__((swift_name("strokeMiterLimit")));
@property float strokeWidth __attribute__((swift_name("strokeWidth")));
@property int32_t style __attribute__((swift_name("style")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeDayOfWeekNames.Companion")))
@interface SharedKotlinx_datetimeDayOfWeekNamesCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_datetimeDayOfWeekNamesCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKotlinx_datetimeDayOfWeekNames *ENGLISH_ABBREVIATED __attribute__((swift_name("ENGLISH_ABBREVIATED")));
@property (readonly) SharedKotlinx_datetimeDayOfWeekNames *ENGLISH_FULL __attribute__((swift_name("ENGLISH_FULL")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_datetimeMonthNames.Companion")))
@interface SharedKotlinx_datetimeMonthNamesCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinx_datetimeMonthNamesCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKotlinx_datetimeMonthNames *ENGLISH_ABBREVIATED __attribute__((swift_name("ENGLISH_ABBREVIATED")));
@property (readonly) SharedKotlinx_datetimeMonthNames *ENGLISH_FULL __attribute__((swift_name("ENGLISH_FULL")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpUrl.Companion")))
@interface SharedKtor_httpUrlCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpUrlCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Ktor_httpParameters")))
@protocol SharedKtor_httpParameters <SharedKtor_utilsStringValues>
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLProtocol")))
@interface SharedKtor_httpURLProtocol : SharedBase
- (instancetype)initWithName:(NSString *)name defaultPort:(int32_t)defaultPort __attribute__((swift_name("init(name:defaultPort:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_httpURLProtocolCompanion *companion __attribute__((swift_name("companion")));
- (SharedKtor_httpURLProtocol *)doCopyName:(NSString *)name defaultPort:(int32_t)defaultPort __attribute__((swift_name("doCopy(name:defaultPort:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t defaultPort __attribute__((swift_name("defaultPort")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpMethod.Companion")))
@interface SharedKtor_httpHttpMethodCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpHttpMethodCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_httpHttpMethod *)parseMethod:(NSString *)method __attribute__((swift_name("parse(method:)")));
@property (readonly) NSArray<SharedKtor_httpHttpMethod *> *DefaultMethods __attribute__((swift_name("DefaultMethods")));
@property (readonly) SharedKtor_httpHttpMethod *Delete __attribute__((swift_name("Delete")));
@property (readonly) SharedKtor_httpHttpMethod *Get __attribute__((swift_name("Get")));
@property (readonly) SharedKtor_httpHttpMethod *Head __attribute__((swift_name("Head")));
@property (readonly) SharedKtor_httpHttpMethod *Options __attribute__((swift_name("Options")));
@property (readonly) SharedKtor_httpHttpMethod *Patch __attribute__((swift_name("Patch")));
@property (readonly) SharedKtor_httpHttpMethod *Post __attribute__((swift_name("Post")));
@property (readonly) SharedKtor_httpHttpMethod *Put __attribute__((swift_name("Put")));
@end

__attribute__((swift_name("KotlinMapEntry")))
@protocol SharedKotlinMapEntry
@required
@property (readonly) id _Nullable key __attribute__((swift_name("key")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("Ktor_httpHeaderValueWithParameters")))
@interface SharedKtor_httpHeaderValueWithParameters : SharedBase
- (instancetype)initWithContent:(NSString *)content parameters:(NSArray<SharedKtor_httpHeaderValueParam *> *)parameters __attribute__((swift_name("init(content:parameters:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKtor_httpHeaderValueWithParametersCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)parameterName:(NSString *)name __attribute__((swift_name("parameter(name:)")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) NSString *content __attribute__((swift_name("content")));
@property (readonly) NSArray<SharedKtor_httpHeaderValueParam *> *parameters __attribute__((swift_name("parameters")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpContentType")))
@interface SharedKtor_httpContentType : SharedKtor_httpHeaderValueWithParameters
- (instancetype)initWithContentType:(NSString *)contentType contentSubtype:(NSString *)contentSubtype parameters:(NSArray<SharedKtor_httpHeaderValueParam *> *)parameters __attribute__((swift_name("init(contentType:contentSubtype:parameters:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithContent:(NSString *)content parameters:(NSArray<SharedKtor_httpHeaderValueParam *> *)parameters __attribute__((swift_name("init(content:parameters:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_httpContentTypeCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)matchPattern:(SharedKtor_httpContentType *)pattern __attribute__((swift_name("match(pattern:)")));
- (BOOL)matchPattern_:(NSString *)pattern __attribute__((swift_name("match(pattern_:)")));
- (SharedKtor_httpContentType *)withParameterName:(NSString *)name value:(NSString *)value __attribute__((swift_name("withParameter(name:value:)")));
- (SharedKtor_httpContentType *)withoutParameters __attribute__((swift_name("withoutParameters()")));
@property (readonly) NSString *contentSubtype __attribute__((swift_name("contentSubtype")));
@property (readonly) NSString *contentType __attribute__((swift_name("contentType")));
@end


/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
__attribute__((swift_name("Kotlinx_coroutines_coreChildHandle")))
@protocol SharedKotlinx_coroutines_coreChildHandle <SharedKotlinx_coroutines_coreDisposableHandle>
@required

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (BOOL)childCancelledCause:(SharedKotlinThrowable *)cause __attribute__((swift_name("childCancelled(cause:)")));

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
@property (readonly) id<SharedKotlinx_coroutines_coreJob> _Nullable parent __attribute__((swift_name("parent")));
@end


/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
__attribute__((swift_name("Kotlinx_coroutines_coreChildJob")))
@protocol SharedKotlinx_coroutines_coreChildJob <SharedKotlinx_coroutines_coreJob>
@required

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (void)parentCancelledParentJob:(id<SharedKotlinx_coroutines_coreParentJob>)parentJob __attribute__((swift_name("parentCancelled(parentJob:)")));
@end

__attribute__((swift_name("KotlinSequence")))
@protocol SharedKotlinSequence
@required
- (id<SharedKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
@end


/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause")))
@protocol SharedKotlinx_coroutines_coreSelectClause
@required
@property (readonly) id clauseObject __attribute__((swift_name("clauseObject")));
@property (readonly) SharedKotlinUnit *(^(^ _Nullable onCancellationConstructor)(id<SharedKotlinx_coroutines_coreSelectInstance>, id _Nullable, id _Nullable))(SharedKotlinThrowable *, id _Nullable, id<SharedKotlinCoroutineContext>) __attribute__((swift_name("onCancellationConstructor")));
@property (readonly) id _Nullable (^processResFunc)(id, id _Nullable, id _Nullable) __attribute__((swift_name("processResFunc")));
@property (readonly) void (^regFunc)(id, id<SharedKotlinx_coroutines_coreSelectInstance>, id _Nullable) __attribute__((swift_name("regFunc")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause0")))
@protocol SharedKotlinx_coroutines_coreSelectClause0 <SharedKotlinx_coroutines_coreSelectClause>
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpStatusCode.Companion")))
@interface SharedKtor_httpHttpStatusCodeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpHttpStatusCodeCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_httpHttpStatusCode *)fromValueValue:(int32_t)value __attribute__((swift_name("fromValue(value:)")));
@property (readonly) SharedKtor_httpHttpStatusCode *Accepted __attribute__((swift_name("Accepted")));
@property (readonly) SharedKtor_httpHttpStatusCode *BadGateway __attribute__((swift_name("BadGateway")));
@property (readonly) SharedKtor_httpHttpStatusCode *BadRequest __attribute__((swift_name("BadRequest")));
@property (readonly) SharedKtor_httpHttpStatusCode *Conflict __attribute__((swift_name("Conflict")));
@property (readonly) SharedKtor_httpHttpStatusCode *Continue __attribute__((swift_name("Continue")));
@property (readonly) SharedKtor_httpHttpStatusCode *Created __attribute__((swift_name("Created")));
@property (readonly) SharedKtor_httpHttpStatusCode *ExpectationFailed __attribute__((swift_name("ExpectationFailed")));
@property (readonly) SharedKtor_httpHttpStatusCode *FailedDependency __attribute__((swift_name("FailedDependency")));
@property (readonly) SharedKtor_httpHttpStatusCode *Forbidden __attribute__((swift_name("Forbidden")));
@property (readonly) SharedKtor_httpHttpStatusCode *Found __attribute__((swift_name("Found")));
@property (readonly) SharedKtor_httpHttpStatusCode *GatewayTimeout __attribute__((swift_name("GatewayTimeout")));
@property (readonly) SharedKtor_httpHttpStatusCode *Gone __attribute__((swift_name("Gone")));
@property (readonly) SharedKtor_httpHttpStatusCode *InsufficientStorage __attribute__((swift_name("InsufficientStorage")));
@property (readonly) SharedKtor_httpHttpStatusCode *InternalServerError __attribute__((swift_name("InternalServerError")));
@property (readonly) SharedKtor_httpHttpStatusCode *LengthRequired __attribute__((swift_name("LengthRequired")));
@property (readonly) SharedKtor_httpHttpStatusCode *Locked __attribute__((swift_name("Locked")));
@property (readonly) SharedKtor_httpHttpStatusCode *MethodNotAllowed __attribute__((swift_name("MethodNotAllowed")));
@property (readonly) SharedKtor_httpHttpStatusCode *MovedPermanently __attribute__((swift_name("MovedPermanently")));
@property (readonly) SharedKtor_httpHttpStatusCode *MultiStatus __attribute__((swift_name("MultiStatus")));
@property (readonly) SharedKtor_httpHttpStatusCode *MultipleChoices __attribute__((swift_name("MultipleChoices")));
@property (readonly) SharedKtor_httpHttpStatusCode *NoContent __attribute__((swift_name("NoContent")));
@property (readonly) SharedKtor_httpHttpStatusCode *NonAuthoritativeInformation __attribute__((swift_name("NonAuthoritativeInformation")));
@property (readonly) SharedKtor_httpHttpStatusCode *NotAcceptable __attribute__((swift_name("NotAcceptable")));
@property (readonly) SharedKtor_httpHttpStatusCode *NotFound __attribute__((swift_name("NotFound")));
@property (readonly) SharedKtor_httpHttpStatusCode *NotImplemented __attribute__((swift_name("NotImplemented")));
@property (readonly) SharedKtor_httpHttpStatusCode *NotModified __attribute__((swift_name("NotModified")));
@property (readonly) SharedKtor_httpHttpStatusCode *OK __attribute__((swift_name("OK")));
@property (readonly) SharedKtor_httpHttpStatusCode *PartialContent __attribute__((swift_name("PartialContent")));
@property (readonly) SharedKtor_httpHttpStatusCode *PayloadTooLarge __attribute__((swift_name("PayloadTooLarge")));
@property (readonly) SharedKtor_httpHttpStatusCode *PaymentRequired __attribute__((swift_name("PaymentRequired")));
@property (readonly) SharedKtor_httpHttpStatusCode *PermanentRedirect __attribute__((swift_name("PermanentRedirect")));
@property (readonly) SharedKtor_httpHttpStatusCode *PreconditionFailed __attribute__((swift_name("PreconditionFailed")));
@property (readonly) SharedKtor_httpHttpStatusCode *Processing __attribute__((swift_name("Processing")));
@property (readonly) SharedKtor_httpHttpStatusCode *ProxyAuthenticationRequired __attribute__((swift_name("ProxyAuthenticationRequired")));
@property (readonly) SharedKtor_httpHttpStatusCode *RequestHeaderFieldTooLarge __attribute__((swift_name("RequestHeaderFieldTooLarge")));
@property (readonly) SharedKtor_httpHttpStatusCode *RequestTimeout __attribute__((swift_name("RequestTimeout")));
@property (readonly) SharedKtor_httpHttpStatusCode *RequestURITooLong __attribute__((swift_name("RequestURITooLong")));
@property (readonly) SharedKtor_httpHttpStatusCode *RequestedRangeNotSatisfiable __attribute__((swift_name("RequestedRangeNotSatisfiable")));
@property (readonly) SharedKtor_httpHttpStatusCode *ResetContent __attribute__((swift_name("ResetContent")));
@property (readonly) SharedKtor_httpHttpStatusCode *SeeOther __attribute__((swift_name("SeeOther")));
@property (readonly) SharedKtor_httpHttpStatusCode *ServiceUnavailable __attribute__((swift_name("ServiceUnavailable")));
@property (readonly) SharedKtor_httpHttpStatusCode *SwitchProxy __attribute__((swift_name("SwitchProxy")));
@property (readonly) SharedKtor_httpHttpStatusCode *SwitchingProtocols __attribute__((swift_name("SwitchingProtocols")));
@property (readonly) SharedKtor_httpHttpStatusCode *TemporaryRedirect __attribute__((swift_name("TemporaryRedirect")));
@property (readonly) SharedKtor_httpHttpStatusCode *TooEarly __attribute__((swift_name("TooEarly")));
@property (readonly) SharedKtor_httpHttpStatusCode *TooManyRequests __attribute__((swift_name("TooManyRequests")));
@property (readonly) SharedKtor_httpHttpStatusCode *Unauthorized __attribute__((swift_name("Unauthorized")));
@property (readonly) SharedKtor_httpHttpStatusCode *UnprocessableEntity __attribute__((swift_name("UnprocessableEntity")));
@property (readonly) SharedKtor_httpHttpStatusCode *UnsupportedMediaType __attribute__((swift_name("UnsupportedMediaType")));
@property (readonly) SharedKtor_httpHttpStatusCode *UpgradeRequired __attribute__((swift_name("UpgradeRequired")));
@property (readonly) SharedKtor_httpHttpStatusCode *UseProxy __attribute__((swift_name("UseProxy")));
@property (readonly) SharedKtor_httpHttpStatusCode *VariantAlsoNegotiates __attribute__((swift_name("VariantAlsoNegotiates")));
@property (readonly) SharedKtor_httpHttpStatusCode *VersionNotSupported __attribute__((swift_name("VersionNotSupported")));
@property (readonly) NSArray<SharedKtor_httpHttpStatusCode *> *allStatusCodes __attribute__((swift_name("allStatusCodes")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsWeekDay")))
@interface SharedKtor_utilsWeekDay : SharedKotlinEnum<SharedKtor_utilsWeekDay *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_utilsWeekDayCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) SharedKtor_utilsWeekDay *monday __attribute__((swift_name("monday")));
@property (class, readonly) SharedKtor_utilsWeekDay *tuesday __attribute__((swift_name("tuesday")));
@property (class, readonly) SharedKtor_utilsWeekDay *wednesday __attribute__((swift_name("wednesday")));
@property (class, readonly) SharedKtor_utilsWeekDay *thursday __attribute__((swift_name("thursday")));
@property (class, readonly) SharedKtor_utilsWeekDay *friday __attribute__((swift_name("friday")));
@property (class, readonly) SharedKtor_utilsWeekDay *saturday __attribute__((swift_name("saturday")));
@property (class, readonly) SharedKtor_utilsWeekDay *sunday __attribute__((swift_name("sunday")));
+ (SharedKotlinArray<SharedKtor_utilsWeekDay *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKtor_utilsWeekDay *> *entries __attribute__((swift_name("entries")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsMonth")))
@interface SharedKtor_utilsMonth : SharedKotlinEnum<SharedKtor_utilsMonth *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedKtor_utilsMonthCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) SharedKtor_utilsMonth *january __attribute__((swift_name("january")));
@property (class, readonly) SharedKtor_utilsMonth *february __attribute__((swift_name("february")));
@property (class, readonly) SharedKtor_utilsMonth *march __attribute__((swift_name("march")));
@property (class, readonly) SharedKtor_utilsMonth *april __attribute__((swift_name("april")));
@property (class, readonly) SharedKtor_utilsMonth *may __attribute__((swift_name("may")));
@property (class, readonly) SharedKtor_utilsMonth *june __attribute__((swift_name("june")));
@property (class, readonly) SharedKtor_utilsMonth *july __attribute__((swift_name("july")));
@property (class, readonly) SharedKtor_utilsMonth *august __attribute__((swift_name("august")));
@property (class, readonly) SharedKtor_utilsMonth *september __attribute__((swift_name("september")));
@property (class, readonly) SharedKtor_utilsMonth *october __attribute__((swift_name("october")));
@property (class, readonly) SharedKtor_utilsMonth *november __attribute__((swift_name("november")));
@property (class, readonly) SharedKtor_utilsMonth *december __attribute__((swift_name("december")));
+ (SharedKotlinArray<SharedKtor_utilsMonth *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKtor_utilsMonth *> *entries __attribute__((swift_name("entries")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsGMTDate.Companion")))
@interface SharedKtor_utilsGMTDateCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_utilsGMTDateCompanion *shared __attribute__((swift_name("shared")));
- (id<SharedKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@property (readonly) SharedKtor_utilsGMTDate *START __attribute__((swift_name("START")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpProtocolVersion.Companion")))
@interface SharedKtor_httpHttpProtocolVersionCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpHttpProtocolVersionCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_httpHttpProtocolVersion *)fromValueName:(NSString *)name major:(int32_t)major minor:(int32_t)minor __attribute__((swift_name("fromValue(name:major:minor:)")));
- (SharedKtor_httpHttpProtocolVersion *)parseValue:(id)value __attribute__((swift_name("parse(value:)")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *HTTP_1_0 __attribute__((swift_name("HTTP_1_0")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *HTTP_1_1 __attribute__((swift_name("HTTP_1_1")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *HTTP_2_0 __attribute__((swift_name("HTTP_2_0")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *QUIC __attribute__((swift_name("QUIC")));
@property (readonly) SharedKtor_httpHttpProtocolVersion *SPDY_3 __attribute__((swift_name("SPDY_3")));
@end

__attribute__((swift_name("KotlinKType")))
@protocol SharedKotlinKType
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
@property (readonly) NSArray<SharedKotlinKTypeProjection *> *arguments __attribute__((swift_name("arguments")));

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
@property (readonly) id<SharedKotlinKClassifier> _Nullable classifier __attribute__((swift_name("classifier")));
@property (readonly) BOOL isMarkedNullable __attribute__((swift_name("isMarkedNullable")));
@end

__attribute__((swift_name("Kotlinx_io_coreRawSource")))
@protocol SharedKotlinx_io_coreRawSource <SharedKotlinAutoCloseable>
@required
- (int64_t)readAtMostToSink:(SharedKotlinx_io_coreBuffer *)sink byteCount:(int64_t)byteCount __attribute__((swift_name("readAtMostTo(sink:byteCount:)")));
@end

__attribute__((swift_name("Kotlinx_io_coreSource")))
@protocol SharedKotlinx_io_coreSource <SharedKotlinx_io_coreRawSource>
@required
- (BOOL)exhausted __attribute__((swift_name("exhausted()")));
- (id<SharedKotlinx_io_coreSource>)peek __attribute__((swift_name("peek()")));
- (int32_t)readAtMostToSink:(SharedKotlinByteArray *)sink startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("readAtMostTo(sink:startIndex:endIndex:)")));
- (int8_t)readByte __attribute__((swift_name("readByte()")));
- (int32_t)readInt __attribute__((swift_name("readInt()")));
- (int64_t)readLong __attribute__((swift_name("readLong()")));
- (int16_t)readShort __attribute__((swift_name("readShort()")));
- (void)readToSink:(id<SharedKotlinx_io_coreRawSink>)sink byteCount:(int64_t)byteCount __attribute__((swift_name("readTo(sink:byteCount:)")));
- (BOOL)requestByteCount:(int64_t)byteCount __attribute__((swift_name("request(byteCount:)")));
- (void)requireByteCount:(int64_t)byteCount __attribute__((swift_name("require(byteCount:)")));
- (void)skipByteCount:(int64_t)byteCount __attribute__((swift_name("skip(byteCount:)")));
- (int64_t)transferToSink:(id<SharedKotlinx_io_coreRawSink>)sink __attribute__((swift_name("transferTo(sink:)")));

/**
 * @note annotations
 *   kotlinx.io.InternalIoApi
*/
@property (readonly) SharedKotlinx_io_coreBuffer *buffer __attribute__((swift_name("buffer")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLBuilder.Companion")))
@interface SharedKtor_httpURLBuilderCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpURLBuilderCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Ktor_httpParametersBuilder")))
@protocol SharedKtor_httpParametersBuilder <SharedKtor_utilsStringValuesBuilder>
@required
@end

__attribute__((swift_name("Ui_textSystemFontFamily")))
@interface SharedUi_textSystemFontFamily : SharedUi_textFontFamily
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textGenericFontFamily")))
@interface SharedUi_textGenericFontFamily : SharedUi_textSystemFontFamily
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textLocale.Companion")))
@interface SharedUi_textLocaleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textLocaleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textLocale *current __attribute__((swift_name("current")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformSpanStyle.Companion")))
@interface SharedUi_textPlatformSpanStyleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textPlatformSpanStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textPlatformSpanStyle *Default __attribute__((swift_name("Default")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontRasterizationSettings")))
@interface SharedUi_textFontRasterizationSettings : SharedBase
- (instancetype)initWithSmoothing:(SharedUi_textFontSmoothing *)smoothing hinting:(SharedUi_textFontHinting *)hinting subpixelPositioning:(BOOL)subpixelPositioning autoHintingForced:(BOOL)autoHintingForced __attribute__((swift_name("init(smoothing:hinting:subpixelPositioning:autoHintingForced:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedUi_textFontRasterizationSettingsCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL autoHintingForced __attribute__((swift_name("autoHintingForced")));
@property (readonly) SharedUi_textFontHinting *hinting __attribute__((swift_name("hinting")));
@property (readonly) SharedUi_textFontSmoothing *smoothing __attribute__((swift_name("smoothing")));
@property (readonly) BOOL subpixelPositioning __attribute__((swift_name("subpixelPositioning")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textPlatformParagraphStyle.Companion")))
@interface SharedUi_textPlatformParagraphStyleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textPlatformParagraphStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textPlatformParagraphStyle *Default __attribute__((swift_name("Default")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface SharedKotlinPair<__covariant A, __covariant B> : SharedBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (SharedKotlinPair<A, B> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@end

__attribute__((swift_name("SkikoNative")))
@interface SharedSkikoNative : SharedBase
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoNativeCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("SkikoManaged")))
@interface SharedSkikoManaged : SharedSkikoNative
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (void)close __attribute__((swift_name("close()")));
@property (readonly) BOOL isClosed __attribute__((swift_name("isClosed")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaint")))
@interface SharedSkikoPaint : SharedSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPaintCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)hasNothingToDraw __attribute__((swift_name("hasNothingToDraw()")));
- (SharedSkikoPaint *)makeClone __attribute__((swift_name("makeClone()")));
- (SharedSkikoPaint *)reset __attribute__((swift_name("reset()")));
- (SharedSkikoPaint *)setARGBA:(int32_t)a r:(int32_t)r g:(int32_t)g b:(int32_t)b __attribute__((swift_name("setARGB(a:r:g:b:)")));
- (SharedSkikoPaint *)setAlphafA:(float)a __attribute__((swift_name("setAlphaf(a:)")));
- (SharedSkikoPaint *)setColor4fColor:(SharedSkikoColor4f *)color colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("setColor4f(color:colorSpace:)")));
- (SharedSkikoPaint *)setStrokeValue:(BOOL)value __attribute__((swift_name("setStroke(value:)")));
@property int32_t alpha __attribute__((swift_name("alpha")));
@property (readonly) float alphaf __attribute__((swift_name("alphaf")));
@property SharedSkikoBlendMode *blendMode __attribute__((swift_name("blendMode")));
@property int32_t color __attribute__((swift_name("color")));
@property SharedSkikoColor4f *color4f __attribute__((swift_name("color4f")));
@property SharedSkikoColorFilter * _Nullable colorFilter __attribute__((swift_name("colorFilter")));
@property SharedSkikoImageFilter * _Nullable imageFilter __attribute__((swift_name("imageFilter")));
@property BOOL isAntiAlias __attribute__((swift_name("isAntiAlias")));
@property BOOL isDither __attribute__((swift_name("isDither")));
@property (readonly) BOOL isSrcOver __attribute__((swift_name("isSrcOver")));
@property SharedSkikoMaskFilter * _Nullable maskFilter __attribute__((swift_name("maskFilter")));
@property SharedSkikoPaintMode *mode __attribute__((swift_name("mode")));
@property SharedSkikoPathEffect * _Nullable pathEffect __attribute__((swift_name("pathEffect")));
@property SharedSkikoShader * _Nullable shader __attribute__((swift_name("shader")));
@property SharedSkikoPaintStrokeCap *strokeCap __attribute__((swift_name("strokeCap")));
@property SharedSkikoPaintStrokeJoin *strokeJoin __attribute__((swift_name("strokeJoin")));
@property float strokeMiter __attribute__((swift_name("strokeMiter")));
@property float strokeWidth __attribute__((swift_name("strokeWidth")));
@end


/**
 * @note annotations
 *   androidx.compose.runtime.Immutable
*/
__attribute__((swift_name("Ui_graphicsColorFilter")))
@interface SharedUi_graphicsColorFilter : SharedBase
@property (class, readonly, getter=companion) SharedUi_graphicsColorFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((swift_name("Ui_graphicsPathEffect")))
@protocol SharedUi_graphicsPathEffect
@required
@end

__attribute__((swift_name("SkikoRefCnt")))
@interface SharedSkikoRefCnt : SharedSkikoManaged

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t refCount __attribute__((swift_name("refCount")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoShader")))
@interface SharedSkikoShader : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoShaderCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoShader *)makeWithColorFilterF:(SharedSkikoColorFilter * _Nullable)f __attribute__((swift_name("makeWithColorFilter(f:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLProtocol.Companion")))
@interface SharedKtor_httpURLProtocolCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpURLProtocolCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_httpURLProtocol *)createOrDefaultName:(NSString *)name __attribute__((swift_name("createOrDefault(name:)")));
@property (readonly) SharedKtor_httpURLProtocol *HTTP __attribute__((swift_name("HTTP")));
@property (readonly) SharedKtor_httpURLProtocol *HTTPS __attribute__((swift_name("HTTPS")));
@property (readonly) SharedKtor_httpURLProtocol *SOCKS __attribute__((swift_name("SOCKS")));
@property (readonly) SharedKtor_httpURLProtocol *WS __attribute__((swift_name("WS")));
@property (readonly) SharedKtor_httpURLProtocol *WSS __attribute__((swift_name("WSS")));
@property (readonly) NSDictionary<NSString *, SharedKtor_httpURLProtocol *> *byName __attribute__((swift_name("byName")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHeaderValueParam")))
@interface SharedKtor_httpHeaderValueParam : SharedBase
- (instancetype)initWithName:(NSString *)name value:(NSString *)value __attribute__((swift_name("init(name:value:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithName:(NSString *)name value:(NSString *)value escapeValue:(BOOL)escapeValue __attribute__((swift_name("init(name:value:escapeValue:)"))) __attribute__((objc_designated_initializer));
- (SharedKtor_httpHeaderValueParam *)doCopyName:(NSString *)name value:(NSString *)value escapeValue:(BOOL)escapeValue __attribute__((swift_name("doCopy(name:value:escapeValue:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL escapeValue __attribute__((swift_name("escapeValue")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHeaderValueWithParameters.Companion")))
@interface SharedKtor_httpHeaderValueWithParametersCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpHeaderValueWithParametersCompanion *shared __attribute__((swift_name("shared")));
- (id _Nullable)parseValue:(NSString *)value init:(id _Nullable (^)(NSString *, NSArray<SharedKtor_httpHeaderValueParam *> *))init __attribute__((swift_name("parse(value:init:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpContentType.Companion")))
@interface SharedKtor_httpContentTypeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_httpContentTypeCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_httpContentType *)parseValue:(NSString *)value __attribute__((swift_name("parse(value:)")));
@property (readonly) SharedKtor_httpContentType *Any __attribute__((swift_name("Any")));
@end


/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
__attribute__((swift_name("Kotlinx_coroutines_coreParentJob")))
@protocol SharedKotlinx_coroutines_coreParentJob <SharedKotlinx_coroutines_coreJob>
@required

/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
- (SharedKotlinCancellationException *)getChildJobCancellationCause __attribute__((swift_name("getChildJobCancellationCause()")));
@end


/**
 * @note annotations
 *   kotlinx.coroutines.InternalCoroutinesApi
*/
__attribute__((swift_name("Kotlinx_coroutines_coreSelectInstance")))
@protocol SharedKotlinx_coroutines_coreSelectInstance
@required
- (void)disposeOnCompletionDisposableHandle:(id<SharedKotlinx_coroutines_coreDisposableHandle>)disposableHandle __attribute__((swift_name("disposeOnCompletion(disposableHandle:)")));
- (void)selectInRegistrationPhaseInternalResult:(id _Nullable)internalResult __attribute__((swift_name("selectInRegistrationPhase(internalResult:)")));
- (BOOL)trySelectClauseObject:(id)clauseObject result:(id _Nullable)result __attribute__((swift_name("trySelect(clauseObject:result:)")));
@property (readonly) id<SharedKotlinCoroutineContext> context __attribute__((swift_name("context")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsWeekDay.Companion")))
@interface SharedKtor_utilsWeekDayCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_utilsWeekDayCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_utilsWeekDay *)fromOrdinal:(int32_t)ordinal __attribute__((swift_name("from(ordinal:)")));
- (SharedKtor_utilsWeekDay *)fromValue:(NSString *)value __attribute__((swift_name("from(value:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsMonth.Companion")))
@interface SharedKtor_utilsMonthCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKtor_utilsMonthCompanion *shared __attribute__((swift_name("shared")));
- (SharedKtor_utilsMonth *)fromOrdinal:(int32_t)ordinal __attribute__((swift_name("from(ordinal:)")));
- (SharedKtor_utilsMonth *)fromValue:(NSString *)value __attribute__((swift_name("from(value:)")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinKTypeProjection")))
@interface SharedKotlinKTypeProjection : SharedBase
- (instancetype)initWithVariance:(SharedKotlinKVariance * _Nullable)variance type:(id<SharedKotlinKType> _Nullable)type __attribute__((swift_name("init(variance:type:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinKTypeProjectionCompanion *companion __attribute__((swift_name("companion")));
- (SharedKotlinKTypeProjection *)doCopyVariance:(SharedKotlinKVariance * _Nullable)variance type:(id<SharedKotlinKType> _Nullable)type __attribute__((swift_name("doCopy(variance:type:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<SharedKotlinKType> _Nullable type __attribute__((swift_name("type")));
@property (readonly) SharedKotlinKVariance * _Nullable variance __attribute__((swift_name("variance")));
@end

__attribute__((swift_name("Kotlinx_io_coreRawSink")))
@protocol SharedKotlinx_io_coreRawSink <SharedKotlinAutoCloseable>
@required
- (void)flush __attribute__((swift_name("flush()")));
- (void)writeSource:(SharedKotlinx_io_coreBuffer *)source byteCount:(int64_t)byteCount __attribute__((swift_name("write(source:byteCount:)")));
@end

__attribute__((swift_name("Kotlinx_io_coreSink")))
@protocol SharedKotlinx_io_coreSink <SharedKotlinx_io_coreRawSink>
@required
- (void)emit __attribute__((swift_name("emit()")));

/**
 * @note annotations
 *   kotlinx.io.InternalIoApi
*/
- (void)hintEmit __attribute__((swift_name("hintEmit()")));
- (int64_t)transferFromSource:(id<SharedKotlinx_io_coreRawSource>)source __attribute__((swift_name("transferFrom(source:)")));
- (void)writeSource:(id<SharedKotlinx_io_coreRawSource>)source byteCount_:(int64_t)byteCount __attribute__((swift_name("write(source:byteCount_:)")));
- (void)writeSource:(SharedKotlinByteArray *)source startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("write(source:startIndex:endIndex:)")));
- (void)writeByteByte:(int8_t)byte __attribute__((swift_name("writeByte(byte:)")));
- (void)writeIntInt:(int32_t)int_ __attribute__((swift_name("writeInt(int:)")));
- (void)writeLongLong:(int64_t)long_ __attribute__((swift_name("writeLong(long:)")));
- (void)writeShortShort:(int16_t)short_ __attribute__((swift_name("writeShort(short:)")));

/**
 * @note annotations
 *   kotlinx.io.InternalIoApi
*/
@property (readonly) SharedKotlinx_io_coreBuffer *buffer __attribute__((swift_name("buffer")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_io_coreBuffer")))
@interface SharedKotlinx_io_coreBuffer : SharedBase <SharedKotlinx_io_coreSource, SharedKotlinx_io_coreSink>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)clear __attribute__((swift_name("clear()")));
- (void)close __attribute__((swift_name("close()")));
- (SharedKotlinx_io_coreBuffer *)doCopy __attribute__((swift_name("doCopy()")));
- (void)doCopyToOut:(SharedKotlinx_io_coreBuffer *)out startIndex:(int64_t)startIndex endIndex:(int64_t)endIndex __attribute__((swift_name("doCopyTo(out:startIndex:endIndex:)")));
- (void)emit __attribute__((swift_name("emit()")));
- (BOOL)exhausted __attribute__((swift_name("exhausted()")));
- (void)flush __attribute__((swift_name("flush()")));
- (int8_t)getPosition:(int64_t)position __attribute__((swift_name("get(position:)")));

/**
 * @note annotations
 *   kotlinx.io.InternalIoApi
*/
- (void)hintEmit __attribute__((swift_name("hintEmit()")));
- (id<SharedKotlinx_io_coreSource>)peek __attribute__((swift_name("peek()")));
- (int64_t)readAtMostToSink:(SharedKotlinx_io_coreBuffer *)sink byteCount:(int64_t)byteCount __attribute__((swift_name("readAtMostTo(sink:byteCount:)")));
- (int32_t)readAtMostToSink:(SharedKotlinByteArray *)sink startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("readAtMostTo(sink:startIndex:endIndex:)")));
- (int8_t)readByte __attribute__((swift_name("readByte()")));
- (int32_t)readInt __attribute__((swift_name("readInt()")));
- (int64_t)readLong __attribute__((swift_name("readLong()")));
- (int16_t)readShort __attribute__((swift_name("readShort()")));
- (void)readToSink:(id<SharedKotlinx_io_coreRawSink>)sink byteCount:(int64_t)byteCount __attribute__((swift_name("readTo(sink:byteCount:)")));
- (BOOL)requestByteCount:(int64_t)byteCount __attribute__((swift_name("request(byteCount:)")));
- (void)requireByteCount:(int64_t)byteCount __attribute__((swift_name("require(byteCount:)")));
- (void)skipByteCount:(int64_t)byteCount __attribute__((swift_name("skip(byteCount:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (int64_t)transferFromSource:(id<SharedKotlinx_io_coreRawSource>)source __attribute__((swift_name("transferFrom(source:)")));
- (int64_t)transferToSink:(id<SharedKotlinx_io_coreRawSink>)sink __attribute__((swift_name("transferTo(sink:)")));
- (void)writeSource:(SharedKotlinx_io_coreBuffer *)source byteCount:(int64_t)byteCount __attribute__((swift_name("write(source:byteCount:)")));
- (void)writeSource:(id<SharedKotlinx_io_coreRawSource>)source byteCount_:(int64_t)byteCount __attribute__((swift_name("write(source:byteCount_:)")));
- (void)writeSource:(SharedKotlinByteArray *)source startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("write(source:startIndex:endIndex:)")));
- (void)writeByteByte:(int8_t)byte __attribute__((swift_name("writeByte(byte:)")));
- (void)writeIntInt:(int32_t)int_ __attribute__((swift_name("writeInt(int:)")));
- (void)writeLongLong:(int64_t)long_ __attribute__((swift_name("writeLong(long:)")));
- (void)writeShortShort:(int16_t)short_ __attribute__((swift_name("writeShort(short:)")));

/**
 * @note annotations
 *   kotlinx.io.InternalIoApi
*/
@property (readonly) SharedKotlinx_io_coreBuffer *buffer __attribute__((swift_name("buffer")));
@property (readonly) int64_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontSmoothing")))
@interface SharedUi_textFontSmoothing : SharedKotlinEnum<SharedUi_textFontSmoothing *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedUi_textFontSmoothing *none __attribute__((swift_name("none")));
@property (class, readonly) SharedUi_textFontSmoothing *antialias __attribute__((swift_name("antialias")));
@property (class, readonly) SharedUi_textFontSmoothing *subpixelantialias __attribute__((swift_name("subpixelantialias")));
+ (SharedKotlinArray<SharedUi_textFontSmoothing *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedUi_textFontSmoothing *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontHinting")))
@interface SharedUi_textFontHinting : SharedKotlinEnum<SharedUi_textFontHinting *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedUi_textFontHinting *none __attribute__((swift_name("none")));
@property (class, readonly) SharedUi_textFontHinting *slight __attribute__((swift_name("slight")));
@property (class, readonly) SharedUi_textFontHinting *normal __attribute__((swift_name("normal")));
@property (class, readonly) SharedUi_textFontHinting *full __attribute__((swift_name("full")));
+ (SharedKotlinArray<SharedUi_textFontHinting *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedUi_textFontHinting *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_textFontRasterizationSettings.Companion")))
@interface SharedUi_textFontRasterizationSettingsCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_textFontRasterizationSettingsCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedUi_textFontRasterizationSettings *PlatformDefault __attribute__((swift_name("PlatformDefault")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoNative.Companion")))
@interface SharedSkikoNativeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoNativeCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) void * _Nullable NullPointer __attribute__((swift_name("NullPointer")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaint.Companion")))
@interface SharedSkikoPaintCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPaintCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColor4f")))
@interface SharedSkikoColor4f : SharedBase
- (instancetype)initWithRgba:(SharedKotlinFloatArray *)rgba __attribute__((swift_name("init(rgba:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithC:(int32_t)c __attribute__((swift_name("init(c:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithR:(float)r g:(float)g b:(float)b a:(float)a __attribute__((swift_name("init(r:g:b:a:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoColor4fCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (SharedKotlinFloatArray *)flatten __attribute__((swift_name("flatten()")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoColor4f *)makeLerpOther:(SharedSkikoColor4f *)other weight:(float)weight __attribute__((swift_name("makeLerp(other:weight:)")));
- (int32_t)toColor __attribute__((swift_name("toColor()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoColor4f *)withA_a:(float)_a __attribute__((swift_name("withA(_a:)")));
- (SharedSkikoColor4f *)withB_b:(float)_b __attribute__((swift_name("withB(_b:)")));
- (SharedSkikoColor4f *)withG_g:(float)_g __attribute__((swift_name("withG(_g:)")));
- (SharedSkikoColor4f *)withR_r:(float)_r __attribute__((swift_name("withR(_r:)")));
@property (readonly) float a __attribute__((swift_name("a")));
@property (readonly) float b __attribute__((swift_name("b")));
@property (readonly) float g __attribute__((swift_name("g")));
@property (readonly) float r __attribute__((swift_name("r")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorSpace")))
@interface SharedSkikoColorSpace : SharedSkikoManaged
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoColorSpaceCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoColor4f *)convertToColor:(SharedSkikoColorSpace * _Nullable)toColor color:(SharedSkikoColor4f *)color __attribute__((swift_name("convert(toColor:color:)")));
@property (readonly) BOOL isGammaCloseToSRGB __attribute__((swift_name("isGammaCloseToSRGB")));
@property (readonly) BOOL isGammaLinear __attribute__((swift_name("isGammaLinear")));
@property (readonly) BOOL isSRGB __attribute__((swift_name("isSRGB")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBlendMode")))
@interface SharedSkikoBlendMode : SharedKotlinEnum<SharedSkikoBlendMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoBlendMode *clear __attribute__((swift_name("clear")));
@property (class, readonly) SharedSkikoBlendMode *src __attribute__((swift_name("src")));
@property (class, readonly) SharedSkikoBlendMode *dst __attribute__((swift_name("dst")));
@property (class, readonly) SharedSkikoBlendMode *srcOver __attribute__((swift_name("srcOver")));
@property (class, readonly) SharedSkikoBlendMode *dstOver __attribute__((swift_name("dstOver")));
@property (class, readonly) SharedSkikoBlendMode *srcIn __attribute__((swift_name("srcIn")));
@property (class, readonly) SharedSkikoBlendMode *dstIn __attribute__((swift_name("dstIn")));
@property (class, readonly) SharedSkikoBlendMode *srcOut __attribute__((swift_name("srcOut")));
@property (class, readonly) SharedSkikoBlendMode *dstOut __attribute__((swift_name("dstOut")));
@property (class, readonly) SharedSkikoBlendMode *srcAtop __attribute__((swift_name("srcAtop")));
@property (class, readonly) SharedSkikoBlendMode *dstAtop __attribute__((swift_name("dstAtop")));
@property (class, readonly) SharedSkikoBlendMode *xor_ __attribute__((swift_name("xor_")));
@property (class, readonly) SharedSkikoBlendMode *plus __attribute__((swift_name("plus")));
@property (class, readonly) SharedSkikoBlendMode *modulate __attribute__((swift_name("modulate")));
@property (class, readonly) SharedSkikoBlendMode *screen __attribute__((swift_name("screen")));
@property (class, readonly) SharedSkikoBlendMode *overlay __attribute__((swift_name("overlay")));
@property (class, readonly) SharedSkikoBlendMode *darken __attribute__((swift_name("darken")));
@property (class, readonly) SharedSkikoBlendMode *lighten __attribute__((swift_name("lighten")));
@property (class, readonly) SharedSkikoBlendMode *colorDodge __attribute__((swift_name("colorDodge")));
@property (class, readonly) SharedSkikoBlendMode *colorBurn __attribute__((swift_name("colorBurn")));
@property (class, readonly) SharedSkikoBlendMode *hardLight __attribute__((swift_name("hardLight")));
@property (class, readonly) SharedSkikoBlendMode *softLight __attribute__((swift_name("softLight")));
@property (class, readonly) SharedSkikoBlendMode *difference __attribute__((swift_name("difference")));
@property (class, readonly) SharedSkikoBlendMode *exclusion __attribute__((swift_name("exclusion")));
@property (class, readonly) SharedSkikoBlendMode *multiply __attribute__((swift_name("multiply")));
@property (class, readonly) SharedSkikoBlendMode *hue __attribute__((swift_name("hue")));
@property (class, readonly) SharedSkikoBlendMode *saturation __attribute__((swift_name("saturation")));
@property (class, readonly) SharedSkikoBlendMode *color __attribute__((swift_name("color")));
@property (class, readonly) SharedSkikoBlendMode *luminosity __attribute__((swift_name("luminosity")));
+ (SharedKotlinArray<SharedSkikoBlendMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoBlendMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorFilter")))
@interface SharedSkikoColorFilter : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoColorFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageFilter")))
@interface SharedSkikoImageFilter : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoImageFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMaskFilter")))
@interface SharedSkikoMaskFilter : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoMaskFilterCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaintMode")))
@interface SharedSkikoPaintMode : SharedKotlinEnum<SharedSkikoPaintMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPaintMode *fill __attribute__((swift_name("fill")));
@property (class, readonly) SharedSkikoPaintMode *stroke __attribute__((swift_name("stroke")));
@property (class, readonly) SharedSkikoPaintMode *strokeAndFill __attribute__((swift_name("strokeAndFill")));
+ (SharedKotlinArray<SharedSkikoPaintMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPaintMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEffect")))
@interface SharedSkikoPathEffect : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPathEffectCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoPathEffect *)makeComposeInner:(SharedSkikoPathEffect * _Nullable)inner __attribute__((swift_name("makeCompose(inner:)")));
- (SharedSkikoPathEffect *)makeSumSecond:(SharedSkikoPathEffect * _Nullable)second __attribute__((swift_name("makeSum(second:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaintStrokeCap")))
@interface SharedSkikoPaintStrokeCap : SharedKotlinEnum<SharedSkikoPaintStrokeCap *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPaintStrokeCap *butt __attribute__((swift_name("butt")));
@property (class, readonly) SharedSkikoPaintStrokeCap *round __attribute__((swift_name("round")));
@property (class, readonly) SharedSkikoPaintStrokeCap *square __attribute__((swift_name("square")));
+ (SharedKotlinArray<SharedSkikoPaintStrokeCap *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPaintStrokeCap *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPaintStrokeJoin")))
@interface SharedSkikoPaintStrokeJoin : SharedKotlinEnum<SharedSkikoPaintStrokeJoin *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPaintStrokeJoin *miter __attribute__((swift_name("miter")));
@property (class, readonly) SharedSkikoPaintStrokeJoin *round __attribute__((swift_name("round")));
@property (class, readonly) SharedSkikoPaintStrokeJoin *bevel __attribute__((swift_name("bevel")));
+ (SharedKotlinArray<SharedSkikoPaintStrokeJoin *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPaintStrokeJoin *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ui_graphicsColorFilter.Companion")))
@interface SharedUi_graphicsColorFilterCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedUi_graphicsColorFilterCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsColorFilter *)colorMatrixColorMatrix:(id)colorMatrix __attribute__((swift_name("colorMatrix(colorMatrix:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsColorFilter *)lightingMultiply:(uint64_t)multiply add:(uint64_t)add __attribute__((swift_name("lighting(multiply:add:)")));

/**
 * @note annotations
 *   androidx.compose.runtime.Stable
*/
- (SharedUi_graphicsColorFilter *)tintColor:(uint64_t)color blendMode:(int32_t)blendMode __attribute__((swift_name("tint(color:blendMode:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoShader.Companion")))
@interface SharedSkikoShaderCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoShaderCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoShader *)makeBlendMode:(SharedSkikoBlendMode *)mode dst:(SharedSkikoShader * _Nullable)dst src:(SharedSkikoShader * _Nullable)src __attribute__((swift_name("makeBlend(mode:dst:src:)")));
- (SharedSkikoShader *)makeColorColor:(int32_t)color __attribute__((swift_name("makeColor(color:)")));
- (SharedSkikoShader *)makeColorColor:(SharedSkikoColor4f *)color space:(SharedSkikoColorSpace * _Nullable)space __attribute__((swift_name("makeColor(color:space:)")));
- (SharedSkikoShader *)makeEmpty __attribute__((swift_name("makeEmpty()")));
- (SharedSkikoShader *)makeFractalNoiseBaseFrequencyX:(float)baseFrequencyX baseFrequencyY:(float)baseFrequencyY numOctaves:(int32_t)numOctaves seed:(float)seed tileSize:(SharedSkikoISize *)tileSize __attribute__((swift_name("makeFractalNoise(baseFrequencyX:baseFrequencyY:numOctaves:seed:tileSize:)")));
- (SharedSkikoShader *)makeLinearGradientP0:(SharedSkikoPoint *)p0 p1:(SharedSkikoPoint *)p1 colors:(SharedKotlinIntArray *)colors __attribute__((swift_name("makeLinearGradient(p0:p1:colors:)")));
- (SharedSkikoShader *)makeLinearGradientP0:(SharedSkikoPoint *)p0 p1:(SharedSkikoPoint *)p1 colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeLinearGradient(p0:p1:colors:positions:)")));
- (SharedSkikoShader *)makeLinearGradientP0:(SharedSkikoPoint *)p0 p1:(SharedSkikoPoint *)p1 colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(p0:p1:colors:positions:style:)")));
- (SharedSkikoShader *)makeLinearGradientP0:(SharedSkikoPoint *)p0 p1:(SharedSkikoPoint *)p1 colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(p0:p1:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeLinearGradientX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(x0:y0:x1:y1:colors:positions:style:)")));
- (SharedSkikoShader *)makeLinearGradientX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeLinearGradient(x0:y0:x1:y1:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeRadialGradientCenter:(SharedSkikoPoint *)center r:(float)r colors:(SharedKotlinIntArray *)colors __attribute__((swift_name("makeRadialGradient(center:r:colors:)")));
- (SharedSkikoShader *)makeRadialGradientCenter:(SharedSkikoPoint *)center r:(float)r colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeRadialGradient(center:r:colors:positions:)")));
- (SharedSkikoShader *)makeRadialGradientCenter:(SharedSkikoPoint *)center r:(float)r colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(center:r:colors:positions:style:)")));
- (SharedSkikoShader *)makeRadialGradientX:(float)x y:(float)y r:(float)r colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(x:y:r:colors:positions:style:)")));
- (SharedSkikoShader *)makeRadialGradientCenter:(SharedSkikoPoint *)center r:(float)r colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(center:r:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeRadialGradientX:(float)x y:(float)y r:(float)r colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeRadialGradient(x:y:r:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeSweepGradientCenter:(SharedSkikoPoint *)center colors:(SharedKotlinIntArray *)colors __attribute__((swift_name("makeSweepGradient(center:colors:)")));
- (SharedSkikoShader *)makeSweepGradientX:(float)x y:(float)y colors:(SharedKotlinIntArray *)colors __attribute__((swift_name("makeSweepGradient(x:y:colors:)")));
- (SharedSkikoShader *)makeSweepGradientCenter:(SharedSkikoPoint *)center colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeSweepGradient(center:colors:positions:)")));
- (SharedSkikoShader *)makeSweepGradientX:(float)x y:(float)y colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeSweepGradient(x:y:colors:positions:)")));
- (SharedSkikoShader *)makeSweepGradientCenter:(SharedSkikoPoint *)center colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(center:colors:positions:style:)")));
- (SharedSkikoShader *)makeSweepGradientX:(float)x y:(float)y colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(x:y:colors:positions:style:)")));
- (SharedSkikoShader *)makeSweepGradientCenter:(SharedSkikoPoint *)center startAngle:(float)startAngle endAngle:(float)endAngle colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(center:startAngle:endAngle:colors:positions:style:)")));
- (SharedSkikoShader *)makeSweepGradientX:(float)x y:(float)y startAngle:(float)startAngle endAngle:(float)endAngle colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(x:y:startAngle:endAngle:colors:positions:style:)")));
- (SharedSkikoShader *)makeSweepGradientCenter:(SharedSkikoPoint *)center startAngle:(float)startAngle endAngle:(float)endAngle colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(center:startAngle:endAngle:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeSweepGradientX:(float)x y:(float)y startAngle:(float)startAngle endAngle:(float)endAngle colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeSweepGradient(x:y:startAngle:endAngle:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeTurbulenceBaseFrequencyX:(float)baseFrequencyX baseFrequencyY:(float)baseFrequencyY numOctaves:(int32_t)numOctaves seed:(float)seed tileSize:(SharedSkikoISize *)tileSize __attribute__((swift_name("makeTurbulence(baseFrequencyX:baseFrequencyY:numOctaves:seed:tileSize:)")));
- (SharedSkikoShader *)makeTwoPointConicalGradientP0:(SharedSkikoPoint *)p0 r0:(float)r0 p1:(SharedSkikoPoint *)p1 r1:(float)r1 colors:(SharedKotlinIntArray *)colors __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:)")));
- (SharedSkikoShader *)makeTwoPointConicalGradientP0:(SharedSkikoPoint *)p0 r0:(float)r0 p1:(SharedSkikoPoint *)p1 r1:(float)r1 colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:positions:)")));
- (SharedSkikoShader *)makeTwoPointConicalGradientP0:(SharedSkikoPoint *)p0 r0:(float)r0 p1:(SharedSkikoPoint *)p1 r1:(float)r1 colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:positions:style:)")));
- (SharedSkikoShader *)makeTwoPointConicalGradientP0:(SharedSkikoPoint *)p0 r0:(float)r0 p1:(SharedSkikoPoint *)p1 r1:(float)r1 colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(p0:r0:p1:r1:colors:cs:positions:style:)")));
- (SharedSkikoShader *)makeTwoPointConicalGradientX0:(float)x0 y0:(float)y0 r0:(float)r0 x1:(float)x1 y1:(float)y1 r1:(float)r1 colors:(SharedKotlinIntArray *)colors positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(x0:y0:r0:x1:y1:r1:colors:positions:style:)")));
- (SharedSkikoShader *)makeTwoPointConicalGradientX0:(float)x0 y0:(float)y0 r0:(float)r0 x1:(float)x1 y1:(float)y1 r1:(float)r1 colors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors cs:(SharedSkikoColorSpace * _Nullable)cs positions:(SharedKotlinFloatArray * _Nullable)positions style:(SharedSkikoGradientStyle *)style __attribute__((swift_name("makeTwoPointConicalGradient(x0:y0:r0:x1:y1:r1:colors:cs:positions:style:)")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinKVariance")))
@interface SharedKotlinKVariance : SharedKotlinEnum<SharedKotlinKVariance *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKotlinKVariance *invariant __attribute__((swift_name("invariant")));
@property (class, readonly) SharedKotlinKVariance *in __attribute__((swift_name("in")));
@property (class, readonly) SharedKotlinKVariance *out __attribute__((swift_name("out")));
+ (SharedKotlinArray<SharedKotlinKVariance *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKotlinKVariance *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinKTypeProjection.Companion")))
@interface SharedKotlinKTypeProjectionCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinKTypeProjectionCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedKotlinKTypeProjection *)contravariantType:(id<SharedKotlinKType>)type __attribute__((swift_name("contravariant(type:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedKotlinKTypeProjection *)covariantType:(id<SharedKotlinKType>)type __attribute__((swift_name("covariant(type:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedKotlinKTypeProjection *)invariantType:(id<SharedKotlinKType>)type __attribute__((swift_name("invariant(type:)")));
@property (readonly) SharedKotlinKTypeProjection *STAR __attribute__((swift_name("STAR")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinFloatArray")))
@interface SharedKotlinFloatArray : SharedBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(SharedFloat *(^)(SharedInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (float)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (SharedKotlinFloatIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(float)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColor4f.Companion")))
@interface SharedSkikoColor4fCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoColor4fCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinFloatArray *)flattenArrayColors:(SharedKotlinArray<SharedSkikoColor4f *> *)colors __attribute__((swift_name("flattenArray(colors:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorSpace.Companion")))
@interface SharedSkikoColorSpaceCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoColorSpaceCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoColorSpace *displayP3 __attribute__((swift_name("displayP3")));
@property (readonly) SharedSkikoColorSpace *sRGB __attribute__((swift_name("sRGB")));
@property (readonly) SharedSkikoColorSpace *sRGBLinear __attribute__((swift_name("sRGBLinear")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorFilter.Companion")))
@interface SharedSkikoColorFilterCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoColorFilterCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoColorFilter *)makeBlendColor:(int32_t)color mode:(SharedSkikoBlendMode *)mode __attribute__((swift_name("makeBlend(color:mode:)")));
- (SharedSkikoColorFilter *)makeComposedOuter:(SharedSkikoColorFilter * _Nullable)outer inner:(SharedSkikoColorFilter * _Nullable)inner __attribute__((swift_name("makeComposed(outer:inner:)")));
- (SharedSkikoColorFilter *)makeHSLAMatrixMatrix:(SharedSkikoColorMatrix *)matrix __attribute__((swift_name("makeHSLAMatrix(matrix:)")));
- (SharedSkikoColorFilter *)makeHighContrastGrayscale:(BOOL)grayscale mode:(SharedSkikoInversionMode *)mode contrast:(float)contrast __attribute__((swift_name("makeHighContrast(grayscale:mode:contrast:)")));
- (SharedSkikoColorFilter *)makeLerpDst:(SharedSkikoColorFilter * _Nullable)dst src:(SharedSkikoColorFilter * _Nullable)src t:(float)t __attribute__((swift_name("makeLerp(dst:src:t:)")));
- (SharedSkikoColorFilter *)makeLightingColorMul:(int32_t)colorMul colorAdd:(int32_t)colorAdd __attribute__((swift_name("makeLighting(colorMul:colorAdd:)")));
- (SharedSkikoColorFilter *)makeMatrixMatrix:(SharedSkikoColorMatrix *)matrix __attribute__((swift_name("makeMatrix(matrix:)")));
- (SharedSkikoColorFilter *)makeOverdrawColors:(SharedKotlinIntArray *)colors __attribute__((swift_name("makeOverdraw(colors:)")));
- (SharedSkikoColorFilter *)makeTableTable:(SharedKotlinByteArray *)table __attribute__((swift_name("makeTable(table:)")));
- (SharedSkikoColorFilter *)makeTableARGBA:(SharedKotlinByteArray * _Nullable)a r:(SharedKotlinByteArray * _Nullable)r g:(SharedKotlinByteArray * _Nullable)g b:(SharedKotlinByteArray * _Nullable)b __attribute__((swift_name("makeTableARGB(a:r:g:b:)")));
@property (readonly) SharedSkikoColorFilter *luma __attribute__((swift_name("luma")));
@property (readonly) SharedSkikoColorFilter *sRGBToLinearGamma __attribute__((swift_name("sRGBToLinearGamma")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageFilter.Companion")))
@interface SharedSkikoImageFilterCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoImageFilterCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoImageFilter *)makeArithmeticK1:(float)k1 k2:(float)k2 k3:(float)k3 k4:(float)k4 enforcePMColor:(BOOL)enforcePMColor bg:(SharedSkikoImageFilter * _Nullable)bg fg:(SharedSkikoImageFilter * _Nullable)fg crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeArithmetic(k1:k2:k3:k4:enforcePMColor:bg:fg:crop:)")));
- (SharedSkikoImageFilter *)makeBlendBlendMode:(SharedSkikoBlendMode *)blendMode bg:(SharedSkikoImageFilter * _Nullable)bg fg:(SharedSkikoImageFilter * _Nullable)fg crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeBlend(blendMode:bg:fg:crop:)")));
- (SharedSkikoImageFilter *)makeBlurSigmaX:(float)sigmaX sigmaY:(float)sigmaY mode:(SharedSkikoFilterTileMode *)mode input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeBlur(sigmaX:sigmaY:mode:input:crop:)")));
- (SharedSkikoImageFilter *)makeColorFilterF:(SharedSkikoColorFilter * _Nullable)f input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeColorFilter(f:input:crop:)")));
- (SharedSkikoImageFilter *)makeComposeOuter:(SharedSkikoImageFilter * _Nullable)outer inner:(SharedSkikoImageFilter * _Nullable)inner __attribute__((swift_name("makeCompose(outer:inner:)")));
- (SharedSkikoImageFilter *)makeDilateRx:(float)rx ry:(float)ry input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDilate(rx:ry:input:crop:)")));
- (SharedSkikoImageFilter *)makeDisplacementMapX:(SharedSkikoColorChannel *)x y:(SharedSkikoColorChannel *)y scale:(float)scale displacement:(SharedSkikoImageFilter * _Nullable)displacement color:(SharedSkikoImageFilter * _Nullable)color crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDisplacementMap(x:y:scale:displacement:color:crop:)")));
- (SharedSkikoImageFilter *)makeDistantLitDiffuseX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale kd:(float)kd input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDistantLitDiffuse(x:y:z:lightColor:surfaceScale:kd:input:crop:)")));
- (SharedSkikoImageFilter *)makeDistantLitSpecularX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale ks:(float)ks shininess:(float)shininess input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDistantLitSpecular(x:y:z:lightColor:surfaceScale:ks:shininess:input:crop:)")));
- (SharedSkikoImageFilter *)makeDropShadowDx:(float)dx dy:(float)dy sigmaX:(float)sigmaX sigmaY:(float)sigmaY color:(int32_t)color input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDropShadow(dx:dy:sigmaX:sigmaY:color:input:crop:)")));
- (SharedSkikoImageFilter *)makeDropShadowOnlyDx:(float)dx dy:(float)dy sigmaX:(float)sigmaX sigmaY:(float)sigmaY color:(int32_t)color input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeDropShadowOnly(dx:dy:sigmaX:sigmaY:color:input:crop:)")));
- (SharedSkikoImageFilter *)makeErodeRx:(float)rx ry:(float)ry input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeErode(rx:ry:input:crop:)")));
- (SharedSkikoImageFilter *)makeImageImage:(SharedSkikoImage *)image __attribute__((swift_name("makeImage(image:)")));
- (SharedSkikoImageFilter *)makeImageImage:(SharedSkikoImage * _Nullable)image src:(SharedSkikoRect *)src dst:(SharedSkikoRect *)dst mode:(id<SharedSkikoSamplingMode>)mode __attribute__((swift_name("makeImage(image:src:dst:mode:)")));
- (SharedSkikoImageFilter *)makeMagnifierR:(SharedSkikoRect *)r zoomAmount:(float)zoomAmount inset:(float)inset samplingMode:(id<SharedSkikoSamplingMode>)samplingMode input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeMagnifier(r:zoomAmount:inset:samplingMode:input:crop:)")));
- (SharedSkikoImageFilter *)makeMatrixConvolutionKernelW:(int32_t)kernelW kernelH:(int32_t)kernelH kernel:(SharedKotlinFloatArray * _Nullable)kernel gain:(float)gain bias:(float)bias offsetX:(int32_t)offsetX offsetY:(int32_t)offsetY tileMode:(SharedSkikoFilterTileMode *)tileMode convolveAlpha:(BOOL)convolveAlpha input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeMatrixConvolution(kernelW:kernelH:kernel:gain:bias:offsetX:offsetY:tileMode:convolveAlpha:input:crop:)")));
- (SharedSkikoImageFilter *)makeMatrixTransformMatrix:(SharedSkikoMatrix33 *)matrix mode:(id<SharedSkikoSamplingMode>)mode input:(SharedSkikoImageFilter * _Nullable)input __attribute__((swift_name("makeMatrixTransform(matrix:mode:input:)")));
- (SharedSkikoImageFilter *)makeMergeFilters:(SharedKotlinArray<SharedSkikoImageFilter *> *)filters crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeMerge(filters:crop:)")));
- (SharedSkikoImageFilter *)makeOffsetDx:(float)dx dy:(float)dy input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeOffset(dx:dy:input:crop:)")));
- (SharedSkikoImageFilter *)makePointLitDiffuseX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale kd:(float)kd input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makePointLitDiffuse(x:y:z:lightColor:surfaceScale:kd:input:crop:)")));
- (SharedSkikoImageFilter *)makePointLitSpecularX:(float)x y:(float)y z:(float)z lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale ks:(float)ks shininess:(float)shininess input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makePointLitSpecular(x:y:z:lightColor:surfaceScale:ks:shininess:input:crop:)")));
- (SharedSkikoImageFilter *)makeRuntimeShaderRuntimeShaderBuilder:(SharedSkikoRuntimeShaderBuilder *)runtimeShaderBuilder shaderNames:(SharedKotlinArray<NSString *> *)shaderNames inputs:(SharedKotlinArray<SharedSkikoImageFilter *> *)inputs __attribute__((swift_name("makeRuntimeShader(runtimeShaderBuilder:shaderNames:inputs:)")));
- (SharedSkikoImageFilter *)makeRuntimeShaderRuntimeShaderBuilder:(SharedSkikoRuntimeShaderBuilder *)runtimeShaderBuilder shaderName:(NSString *)shaderName input:(SharedSkikoImageFilter * _Nullable)input __attribute__((swift_name("makeRuntimeShader(runtimeShaderBuilder:shaderName:input:)")));
- (SharedSkikoImageFilter *)makeShaderShader:(SharedSkikoShader *)shader dither:(BOOL)dither crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeShader(shader:dither:crop:)")));
- (SharedSkikoImageFilter *)makeSpotLitDiffuseX0:(float)x0 y0:(float)y0 z0:(float)z0 x1:(float)x1 y1:(float)y1 z1:(float)z1 falloffExponent:(float)falloffExponent cutoffAngle:(float)cutoffAngle lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale kd:(float)kd input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeSpotLitDiffuse(x0:y0:z0:x1:y1:z1:falloffExponent:cutoffAngle:lightColor:surfaceScale:kd:input:crop:)")));
- (SharedSkikoImageFilter *)makeSpotLitSpecularX0:(float)x0 y0:(float)y0 z0:(float)z0 x1:(float)x1 y1:(float)y1 z1:(float)z1 falloffExponent:(float)falloffExponent cutoffAngle:(float)cutoffAngle lightColor:(int32_t)lightColor surfaceScale:(float)surfaceScale ks:(float)ks shininess:(float)shininess input:(SharedSkikoImageFilter * _Nullable)input crop:(SharedSkikoIRect * _Nullable)crop __attribute__((swift_name("makeSpotLitSpecular(x0:y0:z0:x1:y1:z1:falloffExponent:cutoffAngle:lightColor:surfaceScale:ks:shininess:input:crop:)")));
- (SharedSkikoImageFilter *)makeTileSrc:(SharedSkikoRect *)src dst:(SharedSkikoRect *)dst input:(SharedSkikoImageFilter * _Nullable)input __attribute__((swift_name("makeTile(src:dst:input:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMaskFilter.Companion")))
@interface SharedSkikoMaskFilterCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoMaskFilterCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoMaskFilter *)makeBlurMode:(SharedSkikoFilterBlurMode *)mode sigma:(float)sigma respectCTM:(BOOL)respectCTM __attribute__((swift_name("makeBlur(mode:sigma:respectCTM:)")));
- (SharedSkikoMaskFilter *)makeClipMin:(int32_t)min max:(int32_t)max __attribute__((swift_name("makeClip(min:max:)")));
- (SharedSkikoMaskFilter *)makeGammaGamma:(float)gamma __attribute__((swift_name("makeGamma(gamma:)")));
- (SharedSkikoMaskFilter *)makeShaderS:(SharedSkikoShader * _Nullable)s __attribute__((swift_name("makeShader(s:)")));
- (SharedSkikoMaskFilter *)makeTableTable:(SharedKotlinByteArray *)table __attribute__((swift_name("makeTable(table:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEffect.Companion")))
@interface SharedSkikoPathEffectCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPathEffectCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoPathEffect *)makeCornerRadius:(float)radius __attribute__((swift_name("makeCorner(radius:)")));
- (SharedSkikoPathEffect *)makeDashIntervals:(SharedKotlinFloatArray *)intervals phase:(float)phase __attribute__((swift_name("makeDash(intervals:phase:)")));
- (SharedSkikoPathEffect *)makeDiscreteSegLength:(float)segLength dev:(float)dev seed:(int32_t)seed __attribute__((swift_name("makeDiscrete(segLength:dev:seed:)")));
- (SharedSkikoPathEffect *)makeLine2DWidth:(float)width matrix:(SharedSkikoMatrix33 *)matrix __attribute__((swift_name("makeLine2D(width:matrix:)")));
- (SharedSkikoPathEffect *)makePath1DPath:(SharedSkikoPath *)path advance:(float)advance phase:(float)phase style:(SharedSkikoPathEffectStyle *)style __attribute__((swift_name("makePath1D(path:advance:phase:style:)")));
- (SharedSkikoPathEffect *)makePath2DMatrix:(SharedSkikoMatrix33 *)matrix path:(SharedSkikoPath *)path __attribute__((swift_name("makePath2D(matrix:path:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoISize")))
@interface SharedSkikoISize : SharedBase
@property (class, readonly, getter=companion) SharedSkikoISizeCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)area __attribute__((swift_name("area()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isZero __attribute__((swift_name("isZero()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPoint")))
@interface SharedSkikoPoint : SharedBase
- (instancetype)initWithX:(float)x y:(float)y __attribute__((swift_name("init(x:y:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoPointCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoPoint *)offsetVec:(SharedSkikoPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (SharedSkikoPoint *)offsetDx:(float)dx dy:(float)dy __attribute__((swift_name("offset(dx:dy:)")));
- (SharedSkikoPoint *)scaleScale:(float)scale __attribute__((swift_name("scale(scale:)")));
- (SharedSkikoPoint *)scaleSx:(float)sx sy:(float)sy __attribute__((swift_name("scale(sx:sy:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) float x __attribute__((swift_name("x")));
@property (readonly) float y __attribute__((swift_name("y")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinIntArray")))
@interface SharedKotlinIntArray : SharedBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(SharedInt *(^)(SharedInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int32_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (SharedKotlinIntIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int32_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoGradientStyle")))
@interface SharedSkikoGradientStyle : SharedBase
- (instancetype)initWithTileMode:(SharedSkikoFilterTileMode *)tileMode isPremul:(BOOL)isPremul localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("init(tileMode:isPremul:localMatrix:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoGradientStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoGradientStyle *)withLocalMatrix_localMatrix:(SharedSkikoMatrix33 *)_localMatrix __attribute__((swift_name("withLocalMatrix(_localMatrix:)")));
- (SharedSkikoGradientStyle *)withPremul_premul:(BOOL)_premul __attribute__((swift_name("withPremul(_premul:)")));
- (SharedSkikoGradientStyle *)withTileMode_tileMode:(SharedSkikoFilterTileMode *)_tileMode __attribute__((swift_name("withTileMode(_tileMode:)")));
@property (readonly) BOOL isPremul __attribute__((swift_name("isPremul")));
@property (readonly) SharedSkikoMatrix33 * _Nullable localMatrix __attribute__((swift_name("localMatrix")));
@property (readonly) SharedSkikoFilterTileMode *tileMode __attribute__((swift_name("tileMode")));
@end

__attribute__((swift_name("KotlinFloatIterator")))
@interface SharedKotlinFloatIterator : SharedBase <SharedKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (SharedFloat *)next __attribute__((swift_name("next()")));
- (float)nextFloat __attribute__((swift_name("nextFloat()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorMatrix")))
@interface SharedSkikoColorMatrix : SharedBase
- (instancetype)initWithMat:(SharedKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoInversionMode")))
@interface SharedSkikoInversionMode : SharedKotlinEnum<SharedSkikoInversionMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoInversionMode *no __attribute__((swift_name("no")));
@property (class, readonly) SharedSkikoInversionMode *brightness __attribute__((swift_name("brightness")));
@property (class, readonly) SharedSkikoInversionMode *lightness __attribute__((swift_name("lightness")));
+ (SharedKotlinArray<SharedSkikoInversionMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoInversionMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIRect")))
@interface SharedSkikoIRect : SharedBase
@property (class, readonly, getter=companion) SharedSkikoIRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoIRect * _Nullable)intersectOther:(SharedSkikoIRect *)other __attribute__((swift_name("intersect(other:)")));
- (SharedSkikoIRect *)offsetVec:(SharedSkikoIPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (SharedSkikoIRect *)offsetDx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("offset(dx:dy:)")));
- (SharedSkikoRect *)toRect __attribute__((swift_name("toRect()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t bottom __attribute__((swift_name("bottom")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) int32_t left __attribute__((swift_name("left")));
@property (readonly) int32_t right __attribute__((swift_name("right")));
@property (readonly) int32_t top __attribute__((swift_name("top")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFilterTileMode")))
@interface SharedSkikoFilterTileMode : SharedKotlinEnum<SharedSkikoFilterTileMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoFilterTileMode *clamp __attribute__((swift_name("clamp")));
@property (class, readonly) SharedSkikoFilterTileMode *repeat __attribute__((swift_name("repeat")));
@property (class, readonly) SharedSkikoFilterTileMode *mirror __attribute__((swift_name("mirror")));
@property (class, readonly) SharedSkikoFilterTileMode *decal __attribute__((swift_name("decal")));
+ (SharedKotlinArray<SharedSkikoFilterTileMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoFilterTileMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorChannel")))
@interface SharedSkikoColorChannel : SharedKotlinEnum<SharedSkikoColorChannel *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoColorChannel *r __attribute__((swift_name("r")));
@property (class, readonly) SharedSkikoColorChannel *g __attribute__((swift_name("g")));
@property (class, readonly) SharedSkikoColorChannel *b __attribute__((swift_name("b")));
@property (class, readonly) SharedSkikoColorChannel *a __attribute__((swift_name("a")));
+ (SharedKotlinArray<SharedSkikoColorChannel *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoColorChannel *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((swift_name("SkikoIHasImageInfo")))
@protocol SharedSkikoIHasImageInfo
@required
@property (readonly) SharedSkikoColorAlphaType *alphaType __attribute__((swift_name("alphaType")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) SharedSkikoColorInfo *colorInfo __attribute__((swift_name("colorInfo")));
@property (readonly) SharedSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) SharedSkikoColorType *colorType __attribute__((swift_name("colorType")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) SharedSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@property (readonly, getter=isEmpty__) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isOpaque __attribute__((swift_name("isOpaque")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImage")))
@interface SharedSkikoImage : SharedSkikoRefCnt <SharedSkikoIHasImageInfo>

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoImageCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoData * _Nullable)encodeToDataFormat:(SharedSkikoEncodedImageFormat *)format quality:(int32_t)quality __attribute__((swift_name("encodeToData(format:quality:)")));
- (SharedSkikoShader *)makeShaderLocalMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(localMatrix:)")));
- (SharedSkikoShader *)makeShaderTmx:(SharedSkikoFilterTileMode *)tmx tmy:(SharedSkikoFilterTileMode *)tmy localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:localMatrix:)")));
- (SharedSkikoShader *)makeShaderTmx:(SharedSkikoFilterTileMode *)tmx tmy:(SharedSkikoFilterTileMode *)tmy sampling:(id<SharedSkikoSamplingMode>)sampling localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:sampling:localMatrix:)")));
- (SharedSkikoPixmap * _Nullable)peekPixels __attribute__((swift_name("peekPixels()")));
- (BOOL)peekPixelsPixmap:(SharedSkikoPixmap * _Nullable)pixmap __attribute__((swift_name("peekPixels(pixmap:)")));
- (BOOL)readPixelsDst:(SharedSkikoBitmap *)dst __attribute__((swift_name("readPixels(dst:)")));
- (BOOL)readPixelsContext:(SharedSkikoDirectContext *)context dst:(SharedSkikoBitmap *)dst __attribute__((swift_name("readPixels(context:dst:)")));
- (BOOL)readPixelsDst:(SharedSkikoBitmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(dst:srcX:srcY:)")));
- (BOOL)readPixelsContext:(SharedSkikoDirectContext *)context dst:(SharedSkikoBitmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(context:dst:srcX:srcY:)")));
- (BOOL)readPixelsDst:(SharedSkikoPixmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY cache:(BOOL)cache __attribute__((swift_name("readPixels(dst:srcX:srcY:cache:)")));
- (BOOL)readPixelsContext:(SharedSkikoDirectContext * _Nullable)context dst:(SharedSkikoBitmap *)dst srcX:(int32_t)srcX srcY:(int32_t)srcY cache:(BOOL)cache __attribute__((swift_name("readPixels(context:dst:srcX:srcY:cache:)")));
- (BOOL)scalePixelsDst:(SharedSkikoPixmap *)dst samplingMode:(id<SharedSkikoSamplingMode>)samplingMode cache:(BOOL)cache __attribute__((swift_name("scalePixels(dst:samplingMode:cache:)")));
@property (readonly) SharedSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@end

__attribute__((swift_name("SkikoRect")))
@interface SharedSkikoRect : SharedBase
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("init(left:top:right:bottom:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoRect *)inflateSpread:(float)spread __attribute__((swift_name("inflate(spread:)")));
- (SharedSkikoRect * _Nullable)intersectOther:(SharedSkikoRect *)other __attribute__((swift_name("intersect(other:)")));
- (SharedSkikoRect *)offsetVec:(SharedSkikoPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (SharedSkikoRect *)offsetDx:(float)dx dy:(float)dy __attribute__((swift_name("offset(dx:dy:)")));
- (SharedSkikoRect *)scaleScale:(float)scale __attribute__((swift_name("scale(scale:)")));
- (SharedSkikoRect *)scaleSx:(float)sx sy:(float)sy __attribute__((swift_name("scale(sx:sy:)")));
- (SharedSkikoIRect *)toIRect __attribute__((swift_name("toIRect()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float bottom __attribute__((swift_name("bottom")));
@property (readonly) float height_ __attribute__((swift_name("height_")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) float left __attribute__((swift_name("left")));
@property (readonly) float right __attribute__((swift_name("right")));
@property (readonly) float top __attribute__((swift_name("top")));
@property (readonly) float width_ __attribute__((swift_name("width_")));
@end

__attribute__((swift_name("SkikoSamplingMode")))
@protocol SharedSkikoSamplingMode
@required
- (int64_t)_pack __attribute__((swift_name("_pack()"))) __attribute__((deprecated("Long can't be used because Long is an object in kotlin/js. Consider using _packedInt1 and _packedInt2")));
- (int32_t)_packedInt1 __attribute__((swift_name("_packedInt1()")));
- (int32_t)_packedInt2 __attribute__((swift_name("_packedInt2()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix33")))
@interface SharedSkikoMatrix33 : SharedBase
- (instancetype)initWithMat:(SharedKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoMatrix33Companion *companion __attribute__((swift_name("companion")));
- (SharedSkikoMatrix44 *)asMatrix44 __attribute__((swift_name("asMatrix44()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoMatrix33 *)makeConcatOther:(SharedSkikoMatrix33 *)other __attribute__((swift_name("makeConcat(other:)")));
- (SharedSkikoMatrix33 *)makePreScaleSx:(float)sx sy:(float)sy __attribute__((swift_name("makePreScale(sx:sy:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeShaderBuilder")))
@interface SharedSkikoRuntimeShaderBuilder : SharedSkikoManaged
- (instancetype)initWithEffect:(SharedSkikoRuntimeEffect *)effect __attribute__((swift_name("init(effect:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoRuntimeShaderBuilderCompanion *companion __attribute__((swift_name("companion")));
- (void)childName:(NSString *)name colorFilter:(SharedSkikoColorFilter *)colorFilter __attribute__((swift_name("child(name:colorFilter:)")));
- (void)childName:(NSString *)name shader:(SharedSkikoShader *)shader __attribute__((swift_name("child(name:shader:)")));
- (SharedSkikoShader *)makeShaderLocalMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(localMatrix:)")));
- (void)uniformName:(NSString *)name value:(float)value __attribute__((swift_name("uniform(name:value:)")));
- (void)uniformName:(NSString *)name value_:(SharedKotlinFloatArray *)value __attribute__((swift_name("uniform(name:value_:)")));
- (void)uniformName:(NSString *)name value__:(int32_t)value __attribute__((swift_name("uniform(name:value__:)")));
- (void)uniformName:(NSString *)name value___:(SharedSkikoMatrix22 *)value __attribute__((swift_name("uniform(name:value___:)")));
- (void)uniformName:(NSString *)name value____:(SharedSkikoMatrix33 *)value __attribute__((swift_name("uniform(name:value____:)")));
- (void)uniformName:(NSString *)name value_____:(SharedSkikoMatrix44 *)value __attribute__((swift_name("uniform(name:value_____:)")));
- (void)uniformName:(NSString *)name value1:(float)value1 value2:(float)value2 __attribute__((swift_name("uniform(name:value1:value2:)")));
- (void)uniformName:(NSString *)name value1:(int32_t)value1 value2_:(int32_t)value2 __attribute__((swift_name("uniform(name:value1:value2_:)")));
- (void)uniformName:(NSString *)name value1:(float)value1 value2:(float)value2 value3:(float)value3 __attribute__((swift_name("uniform(name:value1:value2:value3:)")));
- (void)uniformName:(NSString *)name value1:(int32_t)value1 value2:(int32_t)value2 value3_:(int32_t)value3 __attribute__((swift_name("uniform(name:value1:value2:value3_:)")));
- (void)uniformName:(NSString *)name value1:(float)value1 value2:(float)value2 value3:(float)value3 value4:(float)value4 __attribute__((swift_name("uniform(name:value1:value2:value3:value4:)")));
- (void)uniformName:(NSString *)name value1:(int32_t)value1 value2:(int32_t)value2 value3:(int32_t)value3 value4_:(int32_t)value4 __attribute__((swift_name("uniform(name:value1:value2:value3:value4_:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFilterBlurMode")))
@interface SharedSkikoFilterBlurMode : SharedKotlinEnum<SharedSkikoFilterBlurMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoFilterBlurMode *normal __attribute__((swift_name("normal")));
@property (class, readonly) SharedSkikoFilterBlurMode *solid __attribute__((swift_name("solid")));
@property (class, readonly) SharedSkikoFilterBlurMode *outer __attribute__((swift_name("outer")));
@property (class, readonly) SharedSkikoFilterBlurMode *inner __attribute__((swift_name("inner")));
+ (SharedKotlinArray<SharedSkikoFilterBlurMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoFilterBlurMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPath")))
@interface SharedSkikoPath : SharedSkikoManaged <SharedKotlinIterable>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPathCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoPath *)addArcOval:(SharedSkikoRect *)oval startAngle:(float)startAngle sweepAngle:(float)sweepAngle __attribute__((swift_name("addArc(oval:startAngle:sweepAngle:)")));
- (SharedSkikoPath *)addCircleX:(float)x y:(float)y radius:(float)radius dir:(SharedSkikoPathDirection *)dir __attribute__((swift_name("addCircle(x:y:radius:dir:)")));
- (SharedSkikoPath *)addOvalOval:(SharedSkikoRect *)oval dir:(SharedSkikoPathDirection *)dir start:(int32_t)start __attribute__((swift_name("addOval(oval:dir:start:)")));
- (SharedSkikoPath *)addPathSrc:(SharedSkikoPath * _Nullable)src extend:(BOOL)extend __attribute__((swift_name("addPath(src:extend:)")));
- (SharedSkikoPath *)addPathSrc:(SharedSkikoPath * _Nullable)src matrix:(SharedSkikoMatrix33 *)matrix extend:(BOOL)extend __attribute__((swift_name("addPath(src:matrix:extend:)")));
- (SharedSkikoPath *)addPathSrc:(SharedSkikoPath * _Nullable)src dx:(float)dx dy:(float)dy extend:(BOOL)extend __attribute__((swift_name("addPath(src:dx:dy:extend:)")));
- (SharedSkikoPath *)addPolyPts:(SharedKotlinArray<SharedSkikoPoint *> *)pts close:(BOOL)close __attribute__((swift_name("addPoly(pts:close:)")));
- (SharedSkikoPath *)addPolyPts:(SharedKotlinFloatArray *)pts close_:(BOOL)close __attribute__((swift_name("addPoly(pts:close_:)")));
- (SharedSkikoPath *)addRRectRrect:(SharedSkikoRRect *)rrect dir:(SharedSkikoPathDirection *)dir start:(int32_t)start __attribute__((swift_name("addRRect(rrect:dir:start:)")));
- (SharedSkikoPath *)addRectRect:(SharedSkikoRect *)rect dir:(SharedSkikoPathDirection *)dir start:(int32_t)start __attribute__((swift_name("addRect(rect:dir:start:)")));
- (SharedSkikoPath *)arcToOval:(SharedSkikoRect *)oval startAngle:(float)startAngle sweepAngle:(float)sweepAngle forceMoveTo:(BOOL)forceMoveTo __attribute__((swift_name("arcTo(oval:startAngle:sweepAngle:forceMoveTo:)")));
- (SharedSkikoPath *)closePath __attribute__((swift_name("closePath()")));
- (SharedSkikoRect *)computeTightBounds __attribute__((swift_name("computeTightBounds()")));
- (SharedSkikoPath *)conicToP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 w:(float)w __attribute__((swift_name("conicTo(p1:p2:w:)")));
- (SharedSkikoPath *)conicToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 w:(float)w __attribute__((swift_name("conicTo(x1:y1:x2:y2:w:)")));
- (BOOL)conservativelyContainsRectRect:(SharedSkikoRect *)rect __attribute__((swift_name("conservativelyContainsRect(rect:)")));
- (BOOL)containsP:(SharedSkikoPoint *)p __attribute__((swift_name("contains(p:)")));
- (BOOL)containsX:(float)x y:(float)y __attribute__((swift_name("contains(x:y:)")));
- (SharedSkikoPath *)cubicToP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 p3:(SharedSkikoPoint *)p3 __attribute__((swift_name("cubicTo(p1:p2:p3:)")));
- (SharedSkikoPath *)cubicToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 x3:(float)x3 y3:(float)y3 __attribute__((swift_name("cubicTo(x1:y1:x2:y2:x3:y3:)")));
- (SharedSkikoPath *)dump __attribute__((swift_name("dump()")));
- (SharedSkikoPath *)dumpHex __attribute__((swift_name("dumpHex()")));
- (SharedSkikoPath *)ellipticalArcToR:(SharedSkikoPoint *)r xAxisRotate:(float)xAxisRotate arc:(SharedSkikoPathEllipseArc *)arc direction:(SharedSkikoPathDirection *)direction xy:(SharedSkikoPoint *)xy __attribute__((swift_name("ellipticalArcTo(r:xAxisRotate:arc:direction:xy:)")));
- (SharedSkikoPath *)ellipticalArcToRx:(float)rx ry:(float)ry xAxisRotate:(float)xAxisRotate arc:(SharedSkikoPathEllipseArc *)arc direction:(SharedSkikoPathDirection *)direction x:(float)x y:(float)y __attribute__((swift_name("ellipticalArcTo(rx:ry:xAxisRotate:arc:direction:x:y:)")));
- (SharedSkikoPoint *)getPointIndex:(int32_t)index __attribute__((swift_name("getPoint(index:)")));
- (int32_t)getPointsPoints:(SharedKotlinArray<SharedSkikoPoint *> * _Nullable)points max:(int32_t)max __attribute__((swift_name("getPoints(points:max:)")));
- (int32_t)getVerbsVerbs:(SharedKotlinArray<SharedSkikoPathVerb *> * _Nullable)verbs max:(int32_t)max __attribute__((swift_name("getVerbs(verbs:max:)")));
- (SharedSkikoPath *)incReserveExtraPtCount:(int32_t)extraPtCount __attribute__((swift_name("incReserve(extraPtCount:)")));
- (BOOL)isInterpolatableCompare:(SharedSkikoPath * _Nullable)compare __attribute__((swift_name("isInterpolatable(compare:)")));
- (SharedSkikoPathSegmentIterator *)iterator __attribute__((swift_name("iterator()")));
- (SharedSkikoPathSegmentIterator *)iteratorForceClose:(BOOL)forceClose __attribute__((swift_name("iterator(forceClose:)")));
- (SharedSkikoPath *)lineToP:(SharedSkikoPoint *)p __attribute__((swift_name("lineTo(p:)")));
- (SharedSkikoPath *)lineToX:(float)x y:(float)y __attribute__((swift_name("lineTo(x:y:)")));
- (SharedSkikoPath *)makeLerpEnding:(SharedSkikoPath * _Nullable)ending weight:(float)weight __attribute__((swift_name("makeLerp(ending:weight:)")));
- (SharedSkikoPath *)moveToP:(SharedSkikoPoint *)p __attribute__((swift_name("moveTo(p:)")));
- (SharedSkikoPath *)moveToX:(float)x y:(float)y __attribute__((swift_name("moveTo(x:y:)")));
- (SharedSkikoPath *)offsetDx:(float)dx dy:(float)dy dst:(SharedSkikoPath * _Nullable)dst __attribute__((swift_name("offset(dx:dy:dst:)")));
- (SharedSkikoPath *)quadToP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 __attribute__((swift_name("quadTo(p1:p2:)")));
- (SharedSkikoPath *)quadToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 __attribute__((swift_name("quadTo(x1:y1:x2:y2:)")));
- (SharedSkikoPath *)rConicToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 w:(float)w __attribute__((swift_name("rConicTo(dx1:dy1:dx2:dy2:w:)")));
- (SharedSkikoPath *)rCubicToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 dx3:(float)dx3 dy3:(float)dy3 __attribute__((swift_name("rCubicTo(dx1:dy1:dx2:dy2:dx3:dy3:)")));
- (SharedSkikoPath *)rEllipticalArcToRx:(float)rx ry:(float)ry xAxisRotate:(float)xAxisRotate arc:(SharedSkikoPathEllipseArc *)arc direction:(SharedSkikoPathDirection *)direction dx:(float)dx dy:(float)dy __attribute__((swift_name("rEllipticalArcTo(rx:ry:xAxisRotate:arc:direction:dx:dy:)")));
- (SharedSkikoPath *)rLineToDx:(float)dx dy:(float)dy __attribute__((swift_name("rLineTo(dx:dy:)")));
- (SharedSkikoPath *)rMoveToDx:(float)dx dy:(float)dy __attribute__((swift_name("rMoveTo(dx:dy:)")));
- (SharedSkikoPath *)rQuadToDx1:(float)dx1 dy1:(float)dy1 dx2:(float)dx2 dy2:(float)dy2 __attribute__((swift_name("rQuadTo(dx1:dy1:dx2:dy2:)")));
- (SharedSkikoPath *)reset __attribute__((swift_name("reset()")));
- (SharedSkikoPath *)reverseAddPathSrc:(SharedSkikoPath * _Nullable)src __attribute__((swift_name("reverseAddPath(src:)")));
- (SharedSkikoPath *)rewind __attribute__((swift_name("rewind()")));
- (SharedKotlinByteArray *)serializeToBytes __attribute__((swift_name("serializeToBytes()")));
- (SharedSkikoPath *)setLastPtX:(float)x y:(float)y __attribute__((swift_name("setLastPt(x:y:)")));
- (SharedSkikoPath *)setVolatileIsVolatile:(BOOL)isVolatile __attribute__((swift_name("setVolatile(isVolatile:)")));
- (SharedSkikoPath *)swapOther:(SharedSkikoPath * _Nullable)other __attribute__((swift_name("swap(other:)")));
- (SharedSkikoPath *)tangentArcToP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 radius:(float)radius __attribute__((swift_name("tangentArcTo(p1:p2:radius:)")));
- (SharedSkikoPath *)tangentArcToX1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 radius:(float)radius __attribute__((swift_name("tangentArcTo(x1:y1:x2:y2:radius:)")));
- (SharedSkikoPath *)transformMatrix:(SharedSkikoMatrix33 *)matrix applyPerspectiveClip:(BOOL)applyPerspectiveClip __attribute__((swift_name("transform(matrix:applyPerspectiveClip:)")));
- (SharedSkikoPath *)transformMatrix:(SharedSkikoMatrix33 *)matrix dst:(SharedSkikoPath * _Nullable)dst applyPerspectiveClip:(BOOL)applyPerspectiveClip __attribute__((swift_name("transform(matrix:dst:applyPerspectiveClip:)")));
- (SharedSkikoPath *)updateBoundsCache __attribute__((swift_name("updateBoundsCache()")));
@property (readonly) void * _Nullable approximateBytesUsed __attribute__((swift_name("approximateBytesUsed")));
@property (readonly) SharedKotlinArray<SharedSkikoPoint *> * _Nullable asLine __attribute__((swift_name("asLine")));
@property (readonly) SharedSkikoRect *bounds __attribute__((swift_name("bounds")));
@property SharedSkikoPathFillMode *fillMode __attribute__((swift_name("fillMode")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) BOOL isConvex __attribute__((swift_name("isConvex")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isFinite __attribute__((swift_name("isFinite")));
@property (readonly) BOOL isLastContourClosed __attribute__((swift_name("isLastContourClosed")));
@property (readonly) SharedSkikoRect * _Nullable isOval __attribute__((swift_name("isOval")));
@property (readonly) SharedSkikoRRect * _Nullable isRRect __attribute__((swift_name("isRRect")));
@property (readonly) SharedSkikoRect * _Nullable isRect __attribute__((swift_name("isRect")));
@property (readonly) BOOL isValid __attribute__((swift_name("isValid")));
@property BOOL isVolatile __attribute__((swift_name("isVolatile")));
@property SharedSkikoPoint *lastPt __attribute__((swift_name("lastPt")));
@property (readonly) SharedKotlinArray<SharedSkikoPoint *> *points __attribute__((swift_name("points")));
@property (readonly) int32_t pointsCount __attribute__((swift_name("pointsCount")));
@property (readonly) int32_t segmentMasks __attribute__((swift_name("segmentMasks")));
@property (readonly) SharedKotlinArray<SharedSkikoPathVerb *> *verbs __attribute__((swift_name("verbs")));
@property (readonly) int32_t verbsCount __attribute__((swift_name("verbsCount")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEffect.Style")))
@interface SharedSkikoPathEffectStyle : SharedKotlinEnum<SharedSkikoPathEffectStyle *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPathEffectStyle *translate __attribute__((swift_name("translate")));
@property (class, readonly) SharedSkikoPathEffectStyle *rotate __attribute__((swift_name("rotate")));
@property (class, readonly) SharedSkikoPathEffectStyle *morph __attribute__((swift_name("morph")));
+ (SharedKotlinArray<SharedSkikoPathEffectStyle *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPathEffectStyle *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoISize.Companion")))
@interface SharedSkikoISizeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoISizeCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoISize *)makeW:(int32_t)w h:(int32_t)h __attribute__((swift_name("make(w:h:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoISize *)makeEmpty __attribute__((swift_name("makeEmpty()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPoint.Companion")))
@interface SharedSkikoPointCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPointCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinFloatArray * _Nullable)flattenArrayPts:(SharedKotlinArray<SharedSkikoPoint *> * _Nullable)pts __attribute__((swift_name("flattenArray(pts:)")));
- (SharedKotlinArray<SharedSkikoPoint *> * _Nullable)fromArrayPts:(SharedKotlinFloatArray * _Nullable)pts __attribute__((swift_name("fromArray(pts:)")));
@property (readonly) SharedSkikoPoint *ZERO __attribute__((swift_name("ZERO")));
@end

__attribute__((swift_name("KotlinIntIterator")))
@interface SharedKotlinIntIterator : SharedBase <SharedKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (SharedInt *)next __attribute__((swift_name("next()")));
- (int32_t)nextInt __attribute__((swift_name("nextInt()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoGradientStyle.Companion")))
@interface SharedSkikoGradientStyleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoGradientStyleCompanion *shared __attribute__((swift_name("shared")));
@property SharedSkikoGradientStyle *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIRect.Companion")))
@interface SharedSkikoIRectCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoIRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoIRect *)makeLTRBL:(int32_t)l t:(int32_t)t r:(int32_t)r b:(int32_t)b __attribute__((swift_name("makeLTRB(l:t:r:b:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoIRect *)makeWHW:(int32_t)w h:(int32_t)h __attribute__((swift_name("makeWH(w:h:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoIRect *)makeXYWHL:(int32_t)l t:(int32_t)t w:(int32_t)w h:(int32_t)h __attribute__((swift_name("makeXYWH(l:t:w:h:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIPoint")))
@interface SharedSkikoIPoint : SharedBase
- (instancetype)initWithX:(int32_t)x y:(int32_t)y __attribute__((swift_name("init(x:y:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoIPointCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoIPoint *)offsetVec:(SharedSkikoIPoint *)vec __attribute__((swift_name("offset(vec:)")));
- (SharedSkikoIPoint *)offsetDx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("offset(dx:dy:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) int32_t x __attribute__((swift_name("x")));
@property (readonly) int32_t y __attribute__((swift_name("y")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorAlphaType")))
@interface SharedSkikoColorAlphaType : SharedKotlinEnum<SharedSkikoColorAlphaType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoColorAlphaType *unknown __attribute__((swift_name("unknown")));
@property (class, readonly) SharedSkikoColorAlphaType *opaque __attribute__((swift_name("opaque")));
@property (class, readonly) SharedSkikoColorAlphaType *premul __attribute__((swift_name("premul")));
@property (class, readonly) SharedSkikoColorAlphaType *unpremul __attribute__((swift_name("unpremul")));
+ (SharedKotlinArray<SharedSkikoColorAlphaType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoColorAlphaType *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorInfo")))
@interface SharedSkikoColorInfo : SharedBase
- (instancetype)initWithColorType:(SharedSkikoColorType *)colorType alphaType:(SharedSkikoColorAlphaType *)alphaType colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("init(colorType:alphaType:colorSpace:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoColorInfoCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoColorInfo *)withAlphaType_alphaType:(SharedSkikoColorAlphaType *)_alphaType __attribute__((swift_name("withAlphaType(_alphaType:)")));
- (SharedSkikoColorInfo *)withColorSpace_colorSpace:(SharedSkikoColorSpace * _Nullable)_colorSpace __attribute__((swift_name("withColorSpace(_colorSpace:)")));
- (SharedSkikoColorInfo *)withColorType_colorType:(SharedSkikoColorType *)_colorType __attribute__((swift_name("withColorType(_colorType:)")));
@property (readonly) SharedSkikoColorAlphaType *alphaType __attribute__((swift_name("alphaType")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) SharedSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) SharedSkikoColorType *colorType __attribute__((swift_name("colorType")));
@property (readonly) BOOL isGammaCloseToSRGB __attribute__((swift_name("isGammaCloseToSRGB")));
@property (readonly) BOOL isOpaque __attribute__((swift_name("isOpaque")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorType")))
@interface SharedSkikoColorType : SharedKotlinEnum<SharedSkikoColorType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoColorTypeCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) SharedSkikoColorType *unknown __attribute__((swift_name("unknown")));
@property (class, readonly) SharedSkikoColorType *alpha8 __attribute__((swift_name("alpha8")));
@property (class, readonly) SharedSkikoColorType *rgb565 __attribute__((swift_name("rgb565")));
@property (class, readonly) SharedSkikoColorType *argb4444 __attribute__((swift_name("argb4444")));
@property (class, readonly) SharedSkikoColorType *rgba8888 __attribute__((swift_name("rgba8888")));
@property (class, readonly) SharedSkikoColorType *rgb888x __attribute__((swift_name("rgb888x")));
@property (class, readonly) SharedSkikoColorType *bgra8888 __attribute__((swift_name("bgra8888")));
@property (class, readonly) SharedSkikoColorType *rgba1010102 __attribute__((swift_name("rgba1010102")));
@property (class, readonly) SharedSkikoColorType *bgra1010102 __attribute__((swift_name("bgra1010102")));
@property (class, readonly) SharedSkikoColorType *rgb101010x __attribute__((swift_name("rgb101010x")));
@property (class, readonly) SharedSkikoColorType *bgr101010x __attribute__((swift_name("bgr101010x")));
@property (class, readonly) SharedSkikoColorType *bgr101010xXr __attribute__((swift_name("bgr101010xXr")));
@property (class, readonly) SharedSkikoColorType *bgra10101010Xr __attribute__((swift_name("bgra10101010Xr")));
@property (class, readonly) SharedSkikoColorType *rgba10x6 __attribute__((swift_name("rgba10x6")));
@property (class, readonly) SharedSkikoColorType *gray8 __attribute__((swift_name("gray8")));
@property (class, readonly) SharedSkikoColorType *rgbaF16norm __attribute__((swift_name("rgbaF16norm")));
@property (class, readonly) SharedSkikoColorType *rgbaF16 __attribute__((swift_name("rgbaF16")));
@property (class, readonly) SharedSkikoColorType *rgbaF32 __attribute__((swift_name("rgbaF32")));
@property (class, readonly) SharedSkikoColorType *r8g8Unorm __attribute__((swift_name("r8g8Unorm")));
@property (class, readonly) SharedSkikoColorType *a16Float __attribute__((swift_name("a16Float")));
@property (class, readonly) SharedSkikoColorType *r16g16Float __attribute__((swift_name("r16g16Float")));
@property (class, readonly) SharedSkikoColorType *a16Unorm __attribute__((swift_name("a16Unorm")));
@property (class, readonly) SharedSkikoColorType *r16g16Unorm __attribute__((swift_name("r16g16Unorm")));
@property (class, readonly) SharedSkikoColorType *r16g16b16a16Unorm __attribute__((swift_name("r16g16b16a16Unorm")));
+ (SharedKotlinArray<SharedSkikoColorType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoColorType *> *entries __attribute__((swift_name("entries")));
- (int64_t)computeOffsetX:(int32_t)x y:(int32_t)y rowBytes:(int64_t)rowBytes __attribute__((swift_name("computeOffset(x:y:rowBytes:)")));
- (float)getAColor:(int8_t)color __attribute__((swift_name("getA(color:)")));
- (float)getAColor_:(int32_t)color __attribute__((swift_name("getA(color_:)")));
- (float)getAColor__:(int16_t)color __attribute__((swift_name("getA(color__:)")));
- (float)getBColor:(int8_t)color __attribute__((swift_name("getB(color:)")));
- (float)getBColor_:(int32_t)color __attribute__((swift_name("getB(color_:)")));
- (float)getBColor__:(int16_t)color __attribute__((swift_name("getB(color__:)")));
- (float)getGColor:(int8_t)color __attribute__((swift_name("getG(color:)")));
- (float)getGColor_:(int32_t)color __attribute__((swift_name("getG(color_:)")));
- (float)getGColor__:(int16_t)color __attribute__((swift_name("getG(color__:)")));
- (float)getRColor:(int8_t)color __attribute__((swift_name("getR(color:)")));
- (float)getRColor_:(int32_t)color __attribute__((swift_name("getR(color_:)")));
- (float)getRColor__:(int16_t)color __attribute__((swift_name("getR(color__:)")));
- (SharedSkikoColorAlphaType * _Nullable)validateAlphaTypeAlphaType:(SharedSkikoColorAlphaType *)alphaType __attribute__((swift_name("validateAlphaType(alphaType:)")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) BOOL isAlwaysOpaque __attribute__((swift_name("isAlwaysOpaque")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageInfo")))
@interface SharedSkikoImageInfo : SharedBase
- (instancetype)initWithColorInfo:(SharedSkikoColorInfo *)colorInfo width:(int32_t)width height:(int32_t)height __attribute__((swift_name("init(colorInfo:width:height:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithWidth:(int32_t)width height:(int32_t)height colorType:(SharedSkikoColorType *)colorType alphaType:(SharedSkikoColorAlphaType *)alphaType __attribute__((swift_name("init(width:height:colorType:alphaType:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithWidth:(int32_t)width height:(int32_t)height colorType:(SharedSkikoColorType *)colorType alphaType:(SharedSkikoColorAlphaType *)alphaType colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("init(width:height:colorType:alphaType:colorSpace:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoImageInfoCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)computeByteSizeRowBytes:(int32_t)rowBytes __attribute__((swift_name("computeByteSize(rowBytes:)")));
- (int32_t)computeMinByteSize __attribute__((swift_name("computeMinByteSize()")));
- (int64_t)computeOffsetX:(int32_t)x y:(int32_t)y rowBytes:(int64_t)rowBytes __attribute__((swift_name("computeOffset(x:y:rowBytes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isRowBytesValidRowBytes:(int64_t)rowBytes __attribute__((swift_name("isRowBytesValid(rowBytes:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoImageInfo *)withColorAlphaTypeAlphaType:(SharedSkikoColorAlphaType *)alphaType __attribute__((swift_name("withColorAlphaType(alphaType:)")));
- (SharedSkikoImageInfo *)withColorInfo_colorInfo:(SharedSkikoColorInfo *)_colorInfo __attribute__((swift_name("withColorInfo(_colorInfo:)")));
- (SharedSkikoImageInfo *)withColorSpaceColorSpace:(SharedSkikoColorSpace *)colorSpace __attribute__((swift_name("withColorSpace(colorSpace:)")));
- (SharedSkikoImageInfo *)withColorTypeColorType:(SharedSkikoColorType *)colorType __attribute__((swift_name("withColorType(colorType:)")));
- (SharedSkikoImageInfo *)withHeight_height:(int32_t)_height __attribute__((swift_name("withHeight(_height:)")));
- (SharedSkikoImageInfo *)withWidth_width:(int32_t)_width __attribute__((swift_name("withWidth(_width:)")));
- (SharedSkikoImageInfo *)withWidthHeightWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("withWidthHeight(width:height:)")));
@property (readonly) SharedSkikoIRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) int32_t bytesPerPixel __attribute__((swift_name("bytesPerPixel")));
@property (readonly) SharedSkikoColorAlphaType *colorAlphaType __attribute__((swift_name("colorAlphaType")));
@property (readonly) SharedSkikoColorInfo *colorInfo __attribute__((swift_name("colorInfo")));
@property (readonly) SharedSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) SharedSkikoColorType *colorType __attribute__((swift_name("colorType")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isGammaCloseToSRGB __attribute__((swift_name("isGammaCloseToSRGB")));
@property (readonly) BOOL isOpaque __attribute__((swift_name("isOpaque")));
@property (readonly) int32_t minRowBytes __attribute__((swift_name("minRowBytes")));
@property (readonly) int32_t shiftPerPixel __attribute__((swift_name("shiftPerPixel")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImage.Companion")))
@interface SharedSkikoImageCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoImageCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoImage *)makeFromBitmapBitmap:(SharedSkikoBitmap *)bitmap __attribute__((swift_name("makeFromBitmap(bitmap:)")));
- (SharedSkikoImage *)makeFromEncodedBytes:(SharedKotlinByteArray *)bytes __attribute__((swift_name("makeFromEncoded(bytes:)")));
- (SharedSkikoImage *)makeFromPixmapPixmap:(SharedSkikoPixmap *)pixmap __attribute__((swift_name("makeFromPixmap(pixmap:)")));
- (SharedSkikoImage *)makeRasterImageInfo:(SharedSkikoImageInfo *)imageInfo bytes:(SharedKotlinByteArray *)bytes rowBytes:(int32_t)rowBytes __attribute__((swift_name("makeRaster(imageInfo:bytes:rowBytes:)")));
- (SharedSkikoImage *)makeRasterImageInfo:(SharedSkikoImageInfo *)imageInfo data:(SharedSkikoData *)data rowBytes:(int32_t)rowBytes __attribute__((swift_name("makeRaster(imageInfo:data:rowBytes:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoData")))
@interface SharedSkikoData : SharedSkikoManaged
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoDataCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (SharedKotlinByteArray *)getBytesOffset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("getBytes(offset:length:)")));
- (SharedSkikoData *)makeCopy __attribute__((swift_name("makeCopy()")));
- (SharedSkikoData *)makeSubsetOffset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("makeSubset(offset:length:)")));
- (void * _Nullable)writableData __attribute__((swift_name("writableData()")));
@property (readonly) SharedKotlinByteArray *bytes __attribute__((swift_name("bytes")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoEncodedImageFormat")))
@interface SharedSkikoEncodedImageFormat : SharedKotlinEnum<SharedSkikoEncodedImageFormat *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoEncodedImageFormat *bmp __attribute__((swift_name("bmp")));
@property (class, readonly) SharedSkikoEncodedImageFormat *gif __attribute__((swift_name("gif")));
@property (class, readonly) SharedSkikoEncodedImageFormat *ico __attribute__((swift_name("ico")));
@property (class, readonly) SharedSkikoEncodedImageFormat *jpeg __attribute__((swift_name("jpeg")));
@property (class, readonly) SharedSkikoEncodedImageFormat *png __attribute__((swift_name("png")));
@property (class, readonly) SharedSkikoEncodedImageFormat *wbmp __attribute__((swift_name("wbmp")));
@property (class, readonly) SharedSkikoEncodedImageFormat *webp __attribute__((swift_name("webp")));
@property (class, readonly) SharedSkikoEncodedImageFormat *pkm __attribute__((swift_name("pkm")));
@property (class, readonly) SharedSkikoEncodedImageFormat *ktx __attribute__((swift_name("ktx")));
@property (class, readonly) SharedSkikoEncodedImageFormat *astc __attribute__((swift_name("astc")));
@property (class, readonly) SharedSkikoEncodedImageFormat *dng __attribute__((swift_name("dng")));
@property (class, readonly) SharedSkikoEncodedImageFormat *heif __attribute__((swift_name("heif")));
+ (SharedKotlinArray<SharedSkikoEncodedImageFormat *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoEncodedImageFormat *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixmap")))
@interface SharedSkikoPixmap : SharedSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPixmapCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)computeByteSize __attribute__((swift_name("computeByteSize()")));
- (BOOL)computeIsOpaque __attribute__((swift_name("computeIsOpaque()")));
- (BOOL)eraseColor:(int32_t)color __attribute__((swift_name("erase(color:)")));
- (BOOL)eraseColor:(int32_t)color subset:(SharedSkikoIRect *)subset __attribute__((swift_name("erase(color:subset:)")));
- (BOOL)extractSubsetSubsetPtr:(void * _Nullable)subsetPtr area:(SharedSkikoIRect *)area __attribute__((swift_name("extractSubset(subsetPtr:area:)")));
- (BOOL)extractSubsetSubset:(SharedSkikoPixmap *)subset area:(SharedSkikoIRect *)area __attribute__((swift_name("extractSubset(subset:area:)")));
- (void * _Nullable)getAddrX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getAddr(x:y:)")));
- (float)getAlphaFX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getAlphaF(x:y:)")));
- (int32_t)getColorX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getColor(x:y:)")));
- (BOOL)readPixelsPixmap:(SharedSkikoPixmap * _Nullable)pixmap __attribute__((swift_name("readPixels(pixmap:)")));
- (BOOL)readPixelsInfo:(SharedSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes __attribute__((swift_name("readPixels(info:addr:rowBytes:)")));
- (BOOL)readPixelsPixmap:(SharedSkikoPixmap *)pixmap srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(pixmap:srcX:srcY:)")));
- (BOOL)readPixelsInfo:(SharedSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(info:addr:rowBytes:srcX:srcY:)")));
- (void)reset __attribute__((swift_name("reset()")));
- (void)resetInfo:(SharedSkikoImageInfo *)info buffer:(SharedSkikoData *)buffer rowBytes:(int32_t)rowBytes __attribute__((swift_name("reset(info:buffer:rowBytes:)")));
- (void)resetInfo:(SharedSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes underlyingMemoryOwner:(SharedSkikoManaged * _Nullable)underlyingMemoryOwner __attribute__((swift_name("reset(info:addr:rowBytes:underlyingMemoryOwner:)")));
- (BOOL)scalePixelsDstPixmap:(SharedSkikoPixmap * _Nullable)dstPixmap samplingMode:(id<SharedSkikoSamplingMode>)samplingMode __attribute__((swift_name("scalePixels(dstPixmap:samplingMode:)")));
- (void)setColorSpaceColorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("setColorSpace(colorSpace:)")));
@property (readonly) void * _Nullable addr __attribute__((swift_name("addr")));
@property (readonly) SharedSkikoData *buffer __attribute__((swift_name("buffer")));
@property (readonly) SharedSkikoImageInfo *info __attribute__((swift_name("info")));
@property (readonly) int32_t rowBytes __attribute__((swift_name("rowBytes")));
@property (readonly) int32_t rowBytesAsPixels __attribute__((swift_name("rowBytesAsPixels")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBitmap")))
@interface SharedSkikoBitmap : SharedSkikoManaged <SharedSkikoIHasImageInfo>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoBitmapCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)doAllocN32PixelsWidth:(int32_t)width height:(int32_t)height opaque:(BOOL)opaque __attribute__((swift_name("doAllocN32Pixels(width:height:opaque:)")));
- (BOOL)doAllocPixels __attribute__((swift_name("doAllocPixels()")));
- (BOOL)doAllocPixelsImageInfo:(SharedSkikoImageInfo *)imageInfo __attribute__((swift_name("doAllocPixels(imageInfo:)")));
- (BOOL)doAllocPixelsInfo:(SharedSkikoImageInfo *)info rowBytes:(int32_t)rowBytes __attribute__((swift_name("doAllocPixels(info:rowBytes:)")));
- (BOOL)doAllocPixelsFlagsImageInfo:(SharedSkikoImageInfo *)imageInfo zeroPixels:(BOOL)zeroPixels __attribute__((swift_name("doAllocPixelsFlags(imageInfo:zeroPixels:)")));
- (int32_t)computeByteSize __attribute__((swift_name("computeByteSize()")));
- (BOOL)computeIsOpaque __attribute__((swift_name("computeIsOpaque()")));
- (BOOL)drawsNothing __attribute__((swift_name("drawsNothing()")));
- (SharedSkikoBitmap *)eraseColor:(int32_t)color __attribute__((swift_name("erase(color:)")));
- (SharedSkikoBitmap *)eraseColor:(int32_t)color area:(SharedSkikoIRect *)area __attribute__((swift_name("erase(color:area:)")));
- (BOOL)extractAlphaDst:(SharedSkikoBitmap *)dst __attribute__((swift_name("extractAlpha(dst:)")));
- (SharedSkikoIPoint * _Nullable)extractAlphaDst:(SharedSkikoBitmap *)dst paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("extractAlpha(dst:paint:)")));
- (BOOL)extractSubsetDst:(SharedSkikoBitmap *)dst subset:(SharedSkikoIRect *)subset __attribute__((swift_name("extractSubset(dst:subset:)")));
- (float)getAlphafX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getAlphaf(x:y:)")));
- (int32_t)getColorX:(int32_t)x y:(int32_t)y __attribute__((swift_name("getColor(x:y:)")));
- (BOOL)installPixelsPixels:(SharedKotlinByteArray * _Nullable)pixels __attribute__((swift_name("installPixels(pixels:)")));
- (BOOL)installPixelsInfo:(SharedSkikoImageInfo *)info pixels:(SharedKotlinByteArray * _Nullable)pixels rowBytes:(int32_t)rowBytes __attribute__((swift_name("installPixels(info:pixels:rowBytes:)")));
- (SharedSkikoBitmap *)makeClone __attribute__((swift_name("makeClone()")));
- (SharedSkikoShader *)makeShaderLocalMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(localMatrix:)")));
- (SharedSkikoShader *)makeShaderTmx:(SharedSkikoFilterTileMode *)tmx tmy:(SharedSkikoFilterTileMode *)tmy localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:localMatrix:)")));
- (SharedSkikoShader *)makeShaderTmx:(SharedSkikoFilterTileMode *)tmx tmy:(SharedSkikoFilterTileMode *)tmy sampling:(id<SharedSkikoSamplingMode>)sampling localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(tmx:tmy:sampling:localMatrix:)")));
- (SharedSkikoBitmap *)notifyPixelsChanged __attribute__((swift_name("notifyPixelsChanged()")));
- (SharedSkikoPixmap * _Nullable)peekPixels __attribute__((swift_name("peekPixels()")));
- (SharedKotlinByteArray * _Nullable)readPixelsDstInfo:(SharedSkikoImageInfo *)dstInfo dstRowBytes:(int32_t)dstRowBytes srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(dstInfo:dstRowBytes:srcX:srcY:)")));
- (SharedSkikoBitmap *)reset __attribute__((swift_name("reset()")));
- (BOOL)setAlphaTypeAlphaType:(SharedSkikoColorAlphaType *)alphaType __attribute__((swift_name("setAlphaType(alphaType:)")));
- (BOOL)setImageInfoImageInfo:(SharedSkikoImageInfo *)imageInfo __attribute__((swift_name("setImageInfo(imageInfo:)")));
- (BOOL)setImageInfoImageInfo:(SharedSkikoImageInfo *)imageInfo rowBytes:(int32_t)rowBytes __attribute__((swift_name("setImageInfo(imageInfo:rowBytes:)")));
- (SharedSkikoBitmap *)setImmutable __attribute__((swift_name("setImmutable()")));
- (SharedSkikoBitmap *)setPixelRefPixelRef:(SharedSkikoPixelRef * _Nullable)pixelRef dx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("setPixelRef(pixelRef:dx:dy:)")));
- (void)swapOther:(SharedSkikoBitmap *)other __attribute__((swift_name("swap(other:)")));
@property (readonly) SharedSkikoIRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) SharedSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@property (readonly) BOOL isImmutable __attribute__((swift_name("isImmutable")));
@property (readonly) BOOL isNull __attribute__((swift_name("isNull")));
@property (readonly) BOOL isReadyToDraw __attribute__((swift_name("isReadyToDraw")));
@property (readonly) SharedSkikoPixelRef * _Nullable pixelRef __attribute__((swift_name("pixelRef")));
@property (readonly) SharedSkikoIPoint *pixelRefOrigin __attribute__((swift_name("pixelRefOrigin")));
@property (readonly) int32_t rowBytes __attribute__((swift_name("rowBytes")));
@property (readonly) int32_t rowBytesAsPixels __attribute__((swift_name("rowBytesAsPixels")));
@property (readonly) SharedSkikoIRect *subset __attribute__((swift_name("subset")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoDirectContext")))
@interface SharedSkikoDirectContext : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoDirectContextCompanion *companion __attribute__((swift_name("companion")));
- (void)abandon __attribute__((swift_name("abandon()")));
- (SharedSkikoDirectContext *)flush __attribute__((swift_name("flush()")));
- (SharedSkikoDirectContext *)flushSurface:(SharedSkikoSurface *)surface __attribute__((swift_name("flush(surface:)")));
- (void)flushAndSubmitSurface:(SharedSkikoSurface *)surface syncCpu:(BOOL)syncCpu __attribute__((swift_name("flushAndSubmit(surface:syncCpu:)")));
- (SharedSkikoDirectContext *)resetAll __attribute__((swift_name("resetAll()")));
- (SharedSkikoDirectContext *)resetGLStates:(SharedKotlinArray<SharedSkikoGLBackendState *> *)states __attribute__((swift_name("resetGL(states:)")));
- (SharedSkikoDirectContext *)resetGLAll __attribute__((swift_name("resetGLAll()")));
- (void)submitSyncCpu:(BOOL)syncCpu __attribute__((swift_name("submit(syncCpu:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRect.Companion")))
@interface SharedSkikoRectCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b __attribute__((swift_name("makeLTRB(l:t:r:b:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRect *)makeWHSize:(SharedSkikoPoint *)size __attribute__((swift_name("makeWH(size:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRect *)makeWHW:(float)w h:(float)h __attribute__((swift_name("makeWH(w:h:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h __attribute__((swift_name("makeXYWH(l:t:w:h:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix33.Companion")))
@interface SharedSkikoMatrix33Companion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoMatrix33Companion *shared __attribute__((swift_name("shared")));
- (SharedSkikoMatrix33 *)makeRotateDeg:(float)deg __attribute__((swift_name("makeRotate(deg:)")));
- (SharedSkikoMatrix33 *)makeRotateDeg:(float)deg pivot:(SharedSkikoPoint *)pivot __attribute__((swift_name("makeRotate(deg:pivot:)")));
- (SharedSkikoMatrix33 *)makeRotateDeg:(float)deg pivotx:(float)pivotx pivoty:(float)pivoty __attribute__((swift_name("makeRotate(deg:pivotx:pivoty:)")));
- (SharedSkikoMatrix33 *)makeScaleS:(float)s __attribute__((swift_name("makeScale(s:)")));
- (SharedSkikoMatrix33 *)makeScaleSx:(float)sx sy:(float)sy __attribute__((swift_name("makeScale(sx:sy:)")));
- (SharedSkikoMatrix33 *)makeSkewSx:(float)sx sy:(float)sy __attribute__((swift_name("makeSkew(sx:sy:)")));
- (SharedSkikoMatrix33 *)makeTranslateDx:(float)dx dy:(float)dy __attribute__((swift_name("makeTranslate(dx:dy:)")));
@property (readonly) SharedSkikoMatrix33 *IDENTITY __attribute__((swift_name("IDENTITY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix44")))
@interface SharedSkikoMatrix44 : SharedBase
- (instancetype)initWithMat:(SharedKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoMatrix44Companion *companion __attribute__((swift_name("companion")));
- (SharedSkikoMatrix33 *)asMatrix33 __attribute__((swift_name("asMatrix33()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeEffect")))
@interface SharedSkikoRuntimeEffect : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoRuntimeEffectCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoShader *)makeShaderUniforms:(SharedSkikoData * _Nullable)uniforms children:(SharedKotlinArray<SharedSkikoShader *> * _Nullable)children localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix __attribute__((swift_name("makeShader(uniforms:children:localMatrix:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeShaderBuilder.Companion")))
@interface SharedSkikoRuntimeShaderBuilderCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRuntimeShaderBuilderCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix22")))
@interface SharedSkikoMatrix22 : SharedBase
- (instancetype)initWithMat:(SharedKotlinFloatArray *)mat __attribute__((swift_name("init(mat:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoMatrix22Companion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinFloatArray *mat __attribute__((swift_name("mat")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPath.Companion")))
@interface SharedSkikoPathCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPathCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinArray<SharedSkikoPoint *> *)convertConicToQuadsP0:(SharedSkikoPoint *)p0 p1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 w:(float)w pow2:(int32_t)pow2 __attribute__((swift_name("convertConicToQuads(p0:p1:p2:w:pow2:)")));
- (BOOL)isCubicDegenerateP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 p3:(SharedSkikoPoint *)p3 p4:(SharedSkikoPoint *)p4 exact:(BOOL)exact __attribute__((swift_name("isCubicDegenerate(p1:p2:p3:p4:exact:)")));
- (BOOL)isLineDegenerateP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 exact:(BOOL)exact __attribute__((swift_name("isLineDegenerate(p1:p2:exact:)")));
- (BOOL)isQuadDegenerateP1:(SharedSkikoPoint *)p1 p2:(SharedSkikoPoint *)p2 p3:(SharedSkikoPoint *)p3 exact:(BOOL)exact __attribute__((swift_name("isQuadDegenerate(p1:p2:p3:exact:)")));
- (SharedSkikoPath * _Nullable)makeCombiningOne:(SharedSkikoPath *)one two:(SharedSkikoPath *)two op:(SharedSkikoPathOp *)op __attribute__((swift_name("makeCombining(one:two:op:)")));
- (SharedSkikoPath *)makeFromBytesData:(SharedKotlinByteArray *)data __attribute__((swift_name("makeFromBytes(data:)")));
- (SharedSkikoPath *)makeFromSVGStringSvg:(NSString *)svg __attribute__((swift_name("makeFromSVGString(svg:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathDirection")))
@interface SharedSkikoPathDirection : SharedKotlinEnum<SharedSkikoPathDirection *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPathDirection *clockwise __attribute__((swift_name("clockwise")));
@property (class, readonly) SharedSkikoPathDirection *counterClockwise __attribute__((swift_name("counterClockwise")));
+ (SharedKotlinArray<SharedSkikoPathDirection *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPathDirection *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRRect")))
@interface SharedSkikoRRect : SharedSkikoRect
- (instancetype)initWithLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom __attribute__((swift_name("init(left:top:right:bottom:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoRRectCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (SharedSkikoRect *)inflateSpread:(float)spread __attribute__((swift_name("inflate(spread:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinFloatArray *radii __attribute__((swift_name("radii")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathEllipseArc")))
@interface SharedSkikoPathEllipseArc : SharedKotlinEnum<SharedSkikoPathEllipseArc *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPathEllipseArc *smaller __attribute__((swift_name("smaller")));
@property (class, readonly) SharedSkikoPathEllipseArc *larger __attribute__((swift_name("larger")));
+ (SharedKotlinArray<SharedSkikoPathEllipseArc *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPathEllipseArc *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathVerb")))
@interface SharedSkikoPathVerb : SharedKotlinEnum<SharedSkikoPathVerb *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPathVerb *move __attribute__((swift_name("move")));
@property (class, readonly) SharedSkikoPathVerb *line __attribute__((swift_name("line")));
@property (class, readonly) SharedSkikoPathVerb *quad __attribute__((swift_name("quad")));
@property (class, readonly) SharedSkikoPathVerb *conic __attribute__((swift_name("conic")));
@property (class, readonly) SharedSkikoPathVerb *cubic __attribute__((swift_name("cubic")));
@property (class, readonly) SharedSkikoPathVerb *close __attribute__((swift_name("close")));
@property (class, readonly) SharedSkikoPathVerb *done __attribute__((swift_name("done")));
+ (SharedKotlinArray<SharedSkikoPathVerb *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPathVerb *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((swift_name("KotlinMutableIterator")))
@protocol SharedKotlinMutableIterator <SharedKotlinIterator>
@required
- (void)remove __attribute__((swift_name("remove()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathSegmentIterator")))
@interface SharedSkikoPathSegmentIterator : SharedSkikoManaged <SharedKotlinMutableIterator>
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPathSegmentIteratorCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (SharedSkikoPathSegment * _Nullable)next __attribute__((swift_name("next()")));
- (void)remove __attribute__((swift_name("remove()")));
@property SharedSkikoPathSegment * _Nullable _nextSegment __attribute__((swift_name("_nextSegment")));
@property (readonly) SharedSkikoPath * _Nullable _path __attribute__((swift_name("_path")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathFillMode")))
@interface SharedSkikoPathFillMode : SharedKotlinEnum<SharedSkikoPathFillMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPathFillMode *winding __attribute__((swift_name("winding")));
@property (class, readonly) SharedSkikoPathFillMode *evenOdd __attribute__((swift_name("evenOdd")));
@property (class, readonly) SharedSkikoPathFillMode *inverseWinding __attribute__((swift_name("inverseWinding")));
@property (class, readonly) SharedSkikoPathFillMode *inverseEvenOdd __attribute__((swift_name("inverseEvenOdd")));
+ (SharedKotlinArray<SharedSkikoPathFillMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPathFillMode *> *entries __attribute__((swift_name("entries")));
- (SharedSkikoPathFillMode *)inverse __attribute__((swift_name("inverse()")));
@property (readonly) BOOL isInverse __attribute__((swift_name("isInverse")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoIPoint.Companion")))
@interface SharedSkikoIPointCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoIPointCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoIPoint *ZERO __attribute__((swift_name("ZERO")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorInfo.Companion")))
@interface SharedSkikoColorInfoCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoColorInfoCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoColorInfo *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoColorType.Companion")))
@interface SharedSkikoColorTypeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoColorTypeCompanion *shared __attribute__((swift_name("shared")));
@property SharedSkikoColorType *N32 __attribute__((swift_name("N32")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoImageInfo.Companion")))
@interface SharedSkikoImageInfoCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoImageInfoCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoImageInfo *)createUsing_ptr:(void * _Nullable)_ptr _nGetImageInfo:(void (^)(id _Nullable, id _Nullable, id _Nullable))_nGetImageInfo __attribute__((swift_name("createUsing(_ptr:_nGetImageInfo:)")));
- (SharedSkikoImageInfo *)makeA8Width:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeA8(width:height:)")));
- (SharedSkikoImageInfo *)makeN32Width:(int32_t)width height:(int32_t)height alphaType:(SharedSkikoColorAlphaType *)alphaType __attribute__((swift_name("makeN32(width:height:alphaType:)")));
- (SharedSkikoImageInfo *)makeN32Width:(int32_t)width height:(int32_t)height alphaType:(SharedSkikoColorAlphaType *)alphaType colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("makeN32(width:height:alphaType:colorSpace:)")));
- (SharedSkikoImageInfo *)makeN32PremulWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeN32Premul(width:height:)")));
- (SharedSkikoImageInfo *)makeN32PremulWidth:(int32_t)width height:(int32_t)height colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace __attribute__((swift_name("makeN32Premul(width:height:colorSpace:)")));
- (SharedSkikoImageInfo *)makeS32Width:(int32_t)width height:(int32_t)height alphaType:(SharedSkikoColorAlphaType *)alphaType __attribute__((swift_name("makeS32(width:height:alphaType:)")));
- (SharedSkikoImageInfo *)makeUnknownWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeUnknown(width:height:)")));
@property (readonly) SharedSkikoImageInfo *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoData.Companion")))
@interface SharedSkikoDataCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoDataCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoData *)makeEmpty __attribute__((swift_name("makeEmpty()")));
- (SharedSkikoData *)makeFromBytesBytes:(SharedKotlinByteArray *)bytes offset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("makeFromBytes(bytes:offset:length:)")));
- (SharedSkikoData *)makeUninitializedLength:(int32_t)length __attribute__((swift_name("makeUninitialized(length:)")));
- (SharedSkikoData *)makeWithoutCopyMemoryAddr:(void * _Nullable)memoryAddr length:(int32_t)length underlyingMemoryOwner:(SharedSkikoManaged *)underlyingMemoryOwner __attribute__((swift_name("makeWithoutCopy(memoryAddr:length:underlyingMemoryOwner:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixmap.Companion")))
@interface SharedSkikoPixmapCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPixmapCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoPixmap *)makeInfo:(SharedSkikoImageInfo *)info buffer:(SharedSkikoData *)buffer rowBytes:(int32_t)rowBytes __attribute__((swift_name("make(info:buffer:rowBytes:)")));
- (SharedSkikoPixmap *)makeInfo:(SharedSkikoImageInfo *)info addr:(void * _Nullable)addr rowBytes:(int32_t)rowBytes underlyingMemoryOwner:(SharedSkikoManaged * _Nullable)underlyingMemoryOwner __attribute__((swift_name("make(info:addr:rowBytes:underlyingMemoryOwner:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBitmap.Companion")))
@interface SharedSkikoBitmapCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoBitmapCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoBitmap *)makeFromImageImage:(SharedSkikoImage *)image __attribute__((swift_name("makeFromImage(image:)")));
- (SharedSkikoBitmap *)makeFromImageImage:(SharedSkikoImage *)image context:(SharedSkikoDirectContext *)context __attribute__((swift_name("makeFromImage(image:context:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixelRef")))
@interface SharedSkikoPixelRef : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPixelRefCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoPixelRef *)notifyPixelsChanged __attribute__((swift_name("notifyPixelsChanged()")));
- (SharedSkikoPixelRef *)setImmutable __attribute__((swift_name("setImmutable()")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) BOOL isImmutable __attribute__((swift_name("isImmutable")));
@property (readonly) void * _Nullable rowBytes __attribute__((swift_name("rowBytes")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoDirectContext.Companion")))
@interface SharedSkikoDirectContextCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoDirectContextCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoDirectContext *)makeDirect3DAdapterPtr:(void * _Nullable)adapterPtr devicePtr:(void * _Nullable)devicePtr queuePtr:(void * _Nullable)queuePtr __attribute__((swift_name("makeDirect3D(adapterPtr:devicePtr:queuePtr:)")));
- (SharedSkikoDirectContext *)makeGL __attribute__((swift_name("makeGL()")));
- (SharedSkikoDirectContext *)makeMetalDevicePtr:(void * _Nullable)devicePtr queuePtr:(void * _Nullable)queuePtr __attribute__((swift_name("makeMetal(devicePtr:queuePtr:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoSurface")))
@interface SharedSkikoSurface : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoSurfaceCompanion *companion __attribute__((swift_name("companion")));
- (void)drawCanvas:(SharedSkikoCanvas * _Nullable)canvas x:(int32_t)x y:(int32_t)y paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("draw(canvas:x:y:paint:)")));
- (void)drawCanvas:(SharedSkikoCanvas * _Nullable)canvas x:(int32_t)x y:(int32_t)y samplingMode:(id<SharedSkikoSamplingMode>)samplingMode paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("draw(canvas:x:y:samplingMode:paint:)")));
- (void)flush __attribute__((swift_name("flush()")));
- (void)flushAndSubmit __attribute__((swift_name("flushAndSubmit()")));
- (void)flushAndSubmitSyncCpu:(BOOL)syncCpu __attribute__((swift_name("flushAndSubmit(syncCpu:)")));
- (SharedSkikoImage *)makeImageSnapshot __attribute__((swift_name("makeImageSnapshot()")));
- (SharedSkikoImage * _Nullable)makeImageSnapshotArea:(SharedSkikoIRect *)area __attribute__((swift_name("makeImageSnapshot(area:)")));
- (SharedSkikoSurface * _Nullable)makeSurfaceImageInfo:(SharedSkikoImageInfo *)imageInfo __attribute__((swift_name("makeSurface(imageInfo:)")));
- (SharedSkikoSurface * _Nullable)makeSurfaceWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeSurface(width:height:)")));
- (void)notifyContentWillChangeMode:(SharedSkikoContentChangeMode *)mode __attribute__((swift_name("notifyContentWillChange(mode:)")));
- (BOOL)peekPixelsPixmap:(SharedSkikoPixmap *)pixmap __attribute__((swift_name("peekPixels(pixmap:)")));
- (BOOL)readPixelsBitmap:(SharedSkikoBitmap * _Nullable)bitmap srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(bitmap:srcX:srcY:)")));
- (BOOL)readPixelsPixmap:(SharedSkikoPixmap * _Nullable)pixmap srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(pixmap:srcX:srcY:)")));
- (void)writePixelsBitmap:(SharedSkikoBitmap * _Nullable)bitmap x:(int32_t)x y:(int32_t)y __attribute__((swift_name("writePixels(bitmap:x:y:)")));
- (void)writePixelsPixmap:(SharedSkikoPixmap * _Nullable)pixmap x:(int32_t)x y:(int32_t)y __attribute__((swift_name("writePixels(pixmap:x:y:)")));
@property (readonly) SharedSkikoCanvas *canvas __attribute__((swift_name("canvas")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@property (readonly) int32_t height __attribute__((swift_name("height")));
@property (readonly) SharedSkikoImageInfo *imageInfo __attribute__((swift_name("imageInfo")));
@property (readonly) BOOL isUnique __attribute__((swift_name("isUnique")));
@property (readonly) SharedSkikoDirectContext * _Nullable recordingContext __attribute__((swift_name("recordingContext")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoGLBackendState")))
@interface SharedSkikoGLBackendState : SharedKotlinEnum<SharedSkikoGLBackendState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoGLBackendState *renderTarget __attribute__((swift_name("renderTarget")));
@property (class, readonly) SharedSkikoGLBackendState *textureBinding __attribute__((swift_name("textureBinding")));
@property (class, readonly) SharedSkikoGLBackendState *view __attribute__((swift_name("view")));
@property (class, readonly) SharedSkikoGLBackendState *blend __attribute__((swift_name("blend")));
@property (class, readonly) SharedSkikoGLBackendState *msaaEnable __attribute__((swift_name("msaaEnable")));
@property (class, readonly) SharedSkikoGLBackendState *vertex __attribute__((swift_name("vertex")));
@property (class, readonly) SharedSkikoGLBackendState *stencil __attribute__((swift_name("stencil")));
@property (class, readonly) SharedSkikoGLBackendState *pixelStore __attribute__((swift_name("pixelStore")));
@property (class, readonly) SharedSkikoGLBackendState *program __attribute__((swift_name("program")));
@property (class, readonly) SharedSkikoGLBackendState *fixedFunction __attribute__((swift_name("fixedFunction")));
@property (class, readonly) SharedSkikoGLBackendState *misc __attribute__((swift_name("misc")));
@property (class, readonly) SharedSkikoGLBackendState *pathRendering __attribute__((swift_name("pathRendering")));
+ (SharedKotlinArray<SharedSkikoGLBackendState *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoGLBackendState *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix44.Companion")))
@interface SharedSkikoMatrix44Companion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoMatrix44Companion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoMatrix44 *IDENTITY __attribute__((swift_name("IDENTITY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRuntimeEffect.Companion")))
@interface SharedSkikoRuntimeEffectCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRuntimeEffectCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoRuntimeEffect *)makeForColorFilterSksl:(NSString *)sksl __attribute__((swift_name("makeForColorFilter(sksl:)")));
- (SharedSkikoRuntimeEffect *)makeForShaderSksl:(NSString *)sksl __attribute__((swift_name("makeForShader(sksl:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatrix22.Companion")))
@interface SharedSkikoMatrix22Companion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoMatrix22Companion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoMatrix22 *IDENTITY __attribute__((swift_name("IDENTITY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathOp")))
@interface SharedSkikoPathOp : SharedKotlinEnum<SharedSkikoPathOp *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPathOp *difference __attribute__((swift_name("difference")));
@property (class, readonly) SharedSkikoPathOp *intersect __attribute__((swift_name("intersect")));
@property (class, readonly) SharedSkikoPathOp *union_ __attribute__((swift_name("union_")));
@property (class, readonly) SharedSkikoPathOp *xor_ __attribute__((swift_name("xor_")));
@property (class, readonly) SharedSkikoPathOp *reverseDifference __attribute__((swift_name("reverseDifference")));
+ (SharedKotlinArray<SharedSkikoPathOp *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPathOp *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRRect.Companion")))
@interface SharedSkikoRRectCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRRectCompanion *shared __attribute__((swift_name("shared")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRRect *)makeComplexLTRBL:(float)l t:(float)t r:(float)r b:(float)b radii:(SharedKotlinFloatArray *)radii __attribute__((swift_name("makeComplexLTRB(l:t:r:b:radii:)")));
- (SharedSkikoRRect *)makeComplexXYWHL:(float)l t:(float)t w:(float)w h:(float)h radii:(SharedKotlinFloatArray *)radii __attribute__((swift_name("makeComplexXYWH(l:t:w:h:radii:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b radius:(float)radius __attribute__((swift_name("makeLTRB(l:t:r:b:radius:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b xRad:(float)xRad yRad:(float)yRad __attribute__((swift_name("makeLTRB(l:t:r:b:xRad:yRad:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRRect *)makeLTRBL:(float)l t:(float)t r:(float)r b:(float)b tlRad:(float)tlRad trRad:(float)trRad brRad:(float)brRad blRad:(float)blRad __attribute__((swift_name("makeLTRB(l:t:r:b:tlRad:trRad:brRad:blRad:)")));

/**
 * @note annotations
 *   kotlin.jvm.JvmStatic
*/
- (SharedSkikoRRect *)makeNinePatchLTRBL:(float)l t:(float)t r:(float)r b:(float)b lRad:(float)lRad tRad:(float)tRad rRad:(float)rRad bRad:(float)bRad __attribute__((swift_name("makeNinePatchLTRB(l:t:r:b:lRad:tRad:rRad:bRad:)")));
- (SharedSkikoRRect *)makeNinePatchXYWHL:(float)l t:(float)t w:(float)w h:(float)h lRad:(float)lRad tRad:(float)tRad rRad:(float)rRad bRad:(float)bRad __attribute__((swift_name("makeNinePatchXYWH(l:t:w:h:lRad:tRad:rRad:bRad:)")));
- (SharedSkikoRRect *)makeOvalLTRBL:(float)l t:(float)t r:(float)r b:(float)b __attribute__((swift_name("makeOvalLTRB(l:t:r:b:)")));
- (SharedSkikoRRect *)makeOvalXYWHL:(float)l t:(float)t w:(float)w h:(float)h __attribute__((swift_name("makeOvalXYWH(l:t:w:h:)")));
- (SharedSkikoRRect *)makePillLTRBL:(float)l t:(float)t r:(float)r b:(float)b __attribute__((swift_name("makePillLTRB(l:t:r:b:)")));
- (SharedSkikoRRect *)makePillXYWHL:(float)l t:(float)t w:(float)w h:(float)h __attribute__((swift_name("makePillXYWH(l:t:w:h:)")));
- (SharedSkikoRRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h radius:(float)radius __attribute__((swift_name("makeXYWH(l:t:w:h:radius:)")));
- (SharedSkikoRRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h xRad:(float)xRad yRad:(float)yRad __attribute__((swift_name("makeXYWH(l:t:w:h:xRad:yRad:)")));
- (SharedSkikoRRect *)makeXYWHL:(float)l t:(float)t w:(float)w h:(float)h tlRad:(float)tlRad trRad:(float)trRad brRad:(float)brRad blRad:(float)blRad __attribute__((swift_name("makeXYWH(l:t:w:h:tlRad:trRad:brRad:blRad:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathSegmentIterator.Companion")))
@interface SharedSkikoPathSegmentIteratorCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPathSegmentIteratorCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoPathSegmentIterator *)makePath:(SharedSkikoPath * _Nullable)path forceClose:(BOOL)forceClose __attribute__((swift_name("make(path:forceClose:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPathSegment")))
@interface SharedSkikoPathSegment : SharedBase
- (instancetype)initWithVerbOrdinal:(int32_t)verbOrdinal x0:(float)x0 y0:(float)y0 isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(verbOrdinal:x0:y0:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 isCloseLine:(BOOL)isCloseLine isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:isCloseLine:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:x2:y2:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 conicWeight:(float)conicWeight isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:x2:y2:conicWeight:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithVerb:(SharedSkikoPathVerb *)verb p0:(SharedSkikoPoint * _Nullable)p0 p1:(SharedSkikoPoint * _Nullable)p1 p2:(SharedSkikoPoint * _Nullable)p2 p3:(SharedSkikoPoint * _Nullable)p3 conicWeight:(float)conicWeight isCloseLine:(BOOL)isCloseLine isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(verb:p0:p1:p2:p3:conicWeight:isCloseLine:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 x2:(float)x2 y2:(float)y2 x3:(float)x3 y3:(float)y3 isClosedContour:(BOOL)isClosedContour __attribute__((swift_name("init(x0:y0:x1:y1:x2:y2:x3:y3:isClosedContour:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float conicWeight __attribute__((swift_name("conicWeight")));
@property (readonly) BOOL isCloseLine __attribute__((swift_name("isCloseLine")));
@property (readonly) BOOL isClosedContour __attribute__((swift_name("isClosedContour")));
@property (readonly) SharedSkikoPoint * _Nullable p0 __attribute__((swift_name("p0")));
@property (readonly) SharedSkikoPoint * _Nullable p1 __attribute__((swift_name("p1")));
@property (readonly) SharedSkikoPoint * _Nullable p2 __attribute__((swift_name("p2")));
@property (readonly) SharedSkikoPoint * _Nullable p3 __attribute__((swift_name("p3")));
@property (readonly) SharedSkikoPathVerb *verb __attribute__((swift_name("verb")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixelRef.Companion")))
@interface SharedSkikoPixelRefCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPixelRefCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoSurface.Companion")))
@interface SharedSkikoSurfaceCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoSurfaceCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoSurface * _Nullable)makeFromBackendRenderTargetContext:(SharedSkikoDirectContext *)context rt:(SharedSkikoBackendRenderTarget *)rt origin:(SharedSkikoSurfaceOrigin *)origin colorFormat:(SharedSkikoSurfaceColorFormat *)colorFormat colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeFromBackendRenderTarget(context:rt:origin:colorFormat:colorSpace:surfaceProps:)")));
- (SharedSkikoSurface *)makeFromMTKViewContext:(SharedSkikoDirectContext *)context mtkViewPtr:(void * _Nullable)mtkViewPtr origin:(SharedSkikoSurfaceOrigin *)origin sampleCount:(int32_t)sampleCount colorFormat:(SharedSkikoSurfaceColorFormat *)colorFormat colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeFromMTKView(context:mtkViewPtr:origin:sampleCount:colorFormat:colorSpace:surfaceProps:)")));
- (SharedSkikoSurface *)makeNullWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeNull(width:height:)")));
- (SharedSkikoSurface *)makeRasterImageInfo:(SharedSkikoImageInfo *)imageInfo __attribute__((swift_name("makeRaster(imageInfo:)")));
- (SharedSkikoSurface *)makeRasterImageInfo:(SharedSkikoImageInfo *)imageInfo rowBytes:(int32_t)rowBytes __attribute__((swift_name("makeRaster(imageInfo:rowBytes:)")));
- (SharedSkikoSurface *)makeRasterImageInfo:(SharedSkikoImageInfo *)imageInfo rowBytes:(int32_t)rowBytes surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeRaster(imageInfo:rowBytes:surfaceProps:)")));
- (SharedSkikoSurface *)makeRasterDirectPixmap:(SharedSkikoPixmap *)pixmap __attribute__((swift_name("makeRasterDirect(pixmap:)")));
- (SharedSkikoSurface *)makeRasterDirectPixmap:(SharedSkikoPixmap *)pixmap surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeRasterDirect(pixmap:surfaceProps:)")));
- (SharedSkikoSurface *)makeRasterDirectImageInfo:(SharedSkikoImageInfo *)imageInfo pixelsPtr:(void * _Nullable)pixelsPtr rowBytes:(int32_t)rowBytes __attribute__((swift_name("makeRasterDirect(imageInfo:pixelsPtr:rowBytes:)")));
- (SharedSkikoSurface *)makeRasterDirectImageInfo:(SharedSkikoImageInfo *)imageInfo pixelsPtr:(void * _Nullable)pixelsPtr rowBytes:(int32_t)rowBytes surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeRasterDirect(imageInfo:pixelsPtr:rowBytes:surfaceProps:)")));
- (SharedSkikoSurface *)makeRasterN32PremulWidth:(int32_t)width height:(int32_t)height __attribute__((swift_name("makeRasterN32Premul(width:height:)")));
- (SharedSkikoSurface *)makeRenderTargetContext:(SharedSkikoDirectContext *)context budgeted:(BOOL)budgeted imageInfo:(SharedSkikoImageInfo *)imageInfo __attribute__((swift_name("makeRenderTarget(context:budgeted:imageInfo:)")));
- (SharedSkikoSurface *)makeRenderTargetContext:(SharedSkikoDirectContext *)context budgeted:(BOOL)budgeted imageInfo:(SharedSkikoImageInfo *)imageInfo sampleCount:(int32_t)sampleCount surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeRenderTarget(context:budgeted:imageInfo:sampleCount:surfaceProps:)")));
- (SharedSkikoSurface *)makeRenderTargetContext:(SharedSkikoDirectContext *)context budgeted:(BOOL)budgeted imageInfo:(SharedSkikoImageInfo *)imageInfo sampleCount:(int32_t)sampleCount origin:(SharedSkikoSurfaceOrigin *)origin surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps __attribute__((swift_name("makeRenderTarget(context:budgeted:imageInfo:sampleCount:origin:surfaceProps:)")));
- (SharedSkikoSurface *)makeRenderTargetContext:(SharedSkikoDirectContext *)context budgeted:(BOOL)budgeted imageInfo:(SharedSkikoImageInfo *)imageInfo sampleCount:(int32_t)sampleCount origin:(SharedSkikoSurfaceOrigin *)origin surfaceProps:(SharedSkikoSurfaceProps * _Nullable)surfaceProps shouldCreateWithMips:(BOOL)shouldCreateWithMips __attribute__((swift_name("makeRenderTarget(context:budgeted:imageInfo:sampleCount:origin:surfaceProps:shouldCreateWithMips:)")));
@end

__attribute__((swift_name("SkikoCanvas")))
@interface SharedSkikoCanvas : SharedSkikoManaged
- (instancetype)initWithBitmap:(SharedSkikoBitmap *)bitmap surfaceProps:(SharedSkikoSurfaceProps *)surfaceProps __attribute__((swift_name("init(bitmap:surfaceProps:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoCanvasCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoCanvas *)clearColor:(int32_t)color __attribute__((swift_name("clear(color:)")));
- (SharedSkikoCanvas *)clipPathP:(SharedSkikoPath *)p __attribute__((swift_name("clipPath(p:)")));
- (SharedSkikoCanvas *)clipPathP:(SharedSkikoPath *)p antiAlias:(BOOL)antiAlias __attribute__((swift_name("clipPath(p:antiAlias:)")));
- (SharedSkikoCanvas *)clipPathP:(SharedSkikoPath *)p mode:(SharedSkikoClipMode *)mode __attribute__((swift_name("clipPath(p:mode:)")));
- (SharedSkikoCanvas *)clipPathP:(SharedSkikoPath *)p mode:(SharedSkikoClipMode *)mode antiAlias:(BOOL)antiAlias __attribute__((swift_name("clipPath(p:mode:antiAlias:)")));
- (SharedSkikoCanvas *)clipRRectR:(SharedSkikoRRect *)r __attribute__((swift_name("clipRRect(r:)")));
- (SharedSkikoCanvas *)clipRRectR:(SharedSkikoRRect *)r antiAlias:(BOOL)antiAlias __attribute__((swift_name("clipRRect(r:antiAlias:)")));
- (SharedSkikoCanvas *)clipRRectR:(SharedSkikoRRect *)r mode:(SharedSkikoClipMode *)mode __attribute__((swift_name("clipRRect(r:mode:)")));
- (SharedSkikoCanvas *)clipRRectR:(SharedSkikoRRect *)r mode:(SharedSkikoClipMode *)mode antiAlias:(BOOL)antiAlias __attribute__((swift_name("clipRRect(r:mode:antiAlias:)")));
- (SharedSkikoCanvas *)clipRectR:(SharedSkikoRect *)r __attribute__((swift_name("clipRect(r:)")));
- (SharedSkikoCanvas *)clipRectR:(SharedSkikoRect *)r antiAlias:(BOOL)antiAlias __attribute__((swift_name("clipRect(r:antiAlias:)")));
- (SharedSkikoCanvas *)clipRectR:(SharedSkikoRect *)r mode:(SharedSkikoClipMode *)mode __attribute__((swift_name("clipRect(r:mode:)")));
- (SharedSkikoCanvas *)clipRectR:(SharedSkikoRect *)r mode:(SharedSkikoClipMode *)mode antiAlias:(BOOL)antiAlias __attribute__((swift_name("clipRect(r:mode:antiAlias:)")));
- (SharedSkikoCanvas *)clipRegionR:(SharedSkikoRegion *)r __attribute__((swift_name("clipRegion(r:)")));
- (SharedSkikoCanvas *)clipRegionR:(SharedSkikoRegion *)r mode:(SharedSkikoClipMode *)mode __attribute__((swift_name("clipRegion(r:mode:)")));
- (SharedSkikoCanvas *)concatMatrix:(SharedSkikoMatrix33 *)matrix __attribute__((swift_name("concat(matrix:)")));
- (SharedSkikoCanvas *)concatMatrix_:(SharedSkikoMatrix44 *)matrix __attribute__((swift_name("concat(matrix_:)")));
- (SharedSkikoCanvas *)drawArcLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom startAngle:(float)startAngle sweepAngle:(float)sweepAngle includeCenter:(BOOL)includeCenter paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawArc(left:top:right:bottom:startAngle:sweepAngle:includeCenter:paint:)")));
- (SharedSkikoCanvas *)drawCircleX:(float)x y:(float)y radius:(float)radius paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawCircle(x:y:radius:paint:)")));
- (SharedSkikoCanvas *)drawDRRectOuter:(SharedSkikoRRect *)outer inner:(SharedSkikoRRect *)inner paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawDRRect(outer:inner:paint:)")));
- (SharedSkikoCanvas *)drawDrawableDrawable:(SharedSkikoDrawable *)drawable __attribute__((swift_name("drawDrawable(drawable:)")));
- (SharedSkikoCanvas *)drawDrawableDrawable:(SharedSkikoDrawable *)drawable matrix:(SharedSkikoMatrix33 * _Nullable)matrix __attribute__((swift_name("drawDrawable(drawable:matrix:)")));
- (SharedSkikoCanvas *)drawDrawableDrawable:(SharedSkikoDrawable *)drawable x:(float)x y:(float)y __attribute__((swift_name("drawDrawable(drawable:x:y:)")));
- (SharedSkikoCanvas *)drawImageImage:(SharedSkikoImage *)image left:(float)left top:(float)top __attribute__((swift_name("drawImage(image:left:top:)")));
- (SharedSkikoCanvas *)drawImageImage:(SharedSkikoImage *)image left:(float)left top:(float)top paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("drawImage(image:left:top:paint:)")));
- (SharedSkikoCanvas *)drawImageNineImage:(SharedSkikoImage *)image center:(SharedSkikoIRect *)center dst:(SharedSkikoRect *)dst filterMode:(SharedSkikoFilterMode *)filterMode paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("drawImageNine(image:center:dst:filterMode:paint:)")));
- (SharedSkikoCanvas *)drawImageRectImage:(SharedSkikoImage *)image dst:(SharedSkikoRect *)dst __attribute__((swift_name("drawImageRect(image:dst:)")));
- (SharedSkikoCanvas *)drawImageRectImage:(SharedSkikoImage *)image dst:(SharedSkikoRect *)dst paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("drawImageRect(image:dst:paint:)")));
- (SharedSkikoCanvas *)drawImageRectImage:(SharedSkikoImage *)image src:(SharedSkikoRect *)src dst:(SharedSkikoRect *)dst __attribute__((swift_name("drawImageRect(image:src:dst:)")));
- (SharedSkikoCanvas *)drawImageRectImage:(SharedSkikoImage *)image src:(SharedSkikoRect *)src dst:(SharedSkikoRect *)dst paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("drawImageRect(image:src:dst:paint:)")));
- (SharedSkikoCanvas *)drawImageRectImage:(SharedSkikoImage *)image src:(SharedSkikoRect *)src dst:(SharedSkikoRect *)dst paint:(SharedSkikoPaint * _Nullable)paint strict:(BOOL)strict __attribute__((swift_name("drawImageRect(image:src:dst:paint:strict:)")));
- (SharedSkikoCanvas *)drawImageRectImage:(SharedSkikoImage *)image src:(SharedSkikoRect *)src dst:(SharedSkikoRect *)dst samplingMode:(id<SharedSkikoSamplingMode>)samplingMode paint:(SharedSkikoPaint * _Nullable)paint strict:(BOOL)strict __attribute__((swift_name("drawImageRect(image:src:dst:samplingMode:paint:strict:)")));
- (SharedSkikoCanvas *)drawLineX0:(float)x0 y0:(float)y0 x1:(float)x1 y1:(float)y1 paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawLine(x0:y0:x1:y1:paint:)")));
- (SharedSkikoCanvas *)drawLinesCoords:(SharedKotlinArray<SharedSkikoPoint *> *)coords paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawLines(coords:paint:)")));
- (SharedSkikoCanvas *)drawLinesCoords:(SharedKotlinFloatArray *)coords paint_:(SharedSkikoPaint *)paint __attribute__((swift_name("drawLines(coords:paint_:)")));
- (SharedSkikoCanvas *)drawOvalR:(SharedSkikoRect *)r paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawOval(r:paint:)")));
- (SharedSkikoCanvas *)drawPaintPaint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPaint(paint:)")));
- (SharedSkikoCanvas *)drawPatchCubics:(SharedKotlinArray<SharedSkikoPoint *> *)cubics colors:(SharedKotlinIntArray *)colors texCoords:(SharedKotlinArray<SharedSkikoPoint *> * _Nullable)texCoords blendMode:(SharedSkikoBlendMode *)blendMode paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPatch(cubics:colors:texCoords:blendMode:paint:)")));
- (SharedSkikoCanvas *)drawPathPath:(SharedSkikoPath *)path paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPath(path:paint:)")));
- (SharedSkikoCanvas *)drawPicturePicture:(SharedSkikoPicture *)picture matrix:(SharedSkikoMatrix33 * _Nullable)matrix paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("drawPicture(picture:matrix:paint:)")));
- (SharedSkikoCanvas *)drawPointX:(float)x y:(float)y paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPoint(x:y:paint:)")));
- (SharedSkikoCanvas *)drawPointsCoords:(SharedKotlinArray<SharedSkikoPoint *> *)coords paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPoints(coords:paint:)")));
- (SharedSkikoCanvas *)drawPointsCoords:(SharedKotlinFloatArray *)coords paint_:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPoints(coords:paint_:)")));
- (SharedSkikoCanvas *)drawPolygonCoords:(SharedKotlinArray<SharedSkikoPoint *> *)coords paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPolygon(coords:paint:)")));
- (SharedSkikoCanvas *)drawPolygonCoords:(SharedKotlinFloatArray *)coords paint_:(SharedSkikoPaint *)paint __attribute__((swift_name("drawPolygon(coords:paint_:)")));
- (SharedSkikoCanvas *)drawRRectR:(SharedSkikoRRect *)r paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawRRect(r:paint:)")));
- (SharedSkikoCanvas *)drawRectR:(SharedSkikoRect *)r paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawRect(r:paint:)")));
- (SharedSkikoCanvas *)drawRectShadowR:(SharedSkikoRect *)r dx:(float)dx dy:(float)dy blur:(float)blur color:(int32_t)color __attribute__((swift_name("drawRectShadow(r:dx:dy:blur:color:)")));
- (SharedSkikoCanvas *)drawRectShadowR:(SharedSkikoRect *)r dx:(float)dx dy:(float)dy blur:(float)blur spread:(float)spread color:(int32_t)color __attribute__((swift_name("drawRectShadow(r:dx:dy:blur:spread:color:)")));
- (SharedSkikoCanvas *)drawRectShadowNoclipR:(SharedSkikoRect *)r dx:(float)dx dy:(float)dy blur:(float)blur spread:(float)spread color:(int32_t)color __attribute__((swift_name("drawRectShadowNoclip(r:dx:dy:blur:spread:color:)")));
- (SharedSkikoCanvas *)drawRegionR:(SharedSkikoRegion *)r paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawRegion(r:paint:)")));
- (SharedSkikoCanvas *)drawStringS:(NSString *)s x:(float)x y:(float)y font:(SharedSkikoFont * _Nullable)font paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawString(s:x:y:font:paint:)")));
- (SharedSkikoCanvas *)drawTextBlobBlob:(SharedSkikoTextBlob *)blob x:(float)x y:(float)y paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawTextBlob(blob:x:y:paint:)")));
- (SharedSkikoCanvas *)drawTextLineLine:(SharedSkikoTextLine *)line x:(float)x y:(float)y paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawTextLine(line:x:y:paint:)")));
- (SharedSkikoCanvas *)drawTriangleFanPositions:(SharedKotlinArray<SharedSkikoPoint *> *)positions colors:(SharedKotlinIntArray * _Nullable)colors texCoords:(SharedKotlinArray<SharedSkikoPoint *> * _Nullable)texCoords indices:(SharedKotlinShortArray * _Nullable)indices blendMode:(SharedSkikoBlendMode *)blendMode paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawTriangleFan(positions:colors:texCoords:indices:blendMode:paint:)")));
- (SharedSkikoCanvas *)drawTriangleStripPositions:(SharedKotlinArray<SharedSkikoPoint *> *)positions colors:(SharedKotlinIntArray * _Nullable)colors texCoords:(SharedKotlinArray<SharedSkikoPoint *> * _Nullable)texCoords indices:(SharedKotlinShortArray * _Nullable)indices blendMode:(SharedSkikoBlendMode *)blendMode paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawTriangleStrip(positions:colors:texCoords:indices:blendMode:paint:)")));
- (SharedSkikoCanvas *)drawTrianglesPositions:(SharedKotlinArray<SharedSkikoPoint *> *)positions colors:(SharedKotlinIntArray * _Nullable)colors texCoords:(SharedKotlinArray<SharedSkikoPoint *> * _Nullable)texCoords indices:(SharedKotlinShortArray * _Nullable)indices blendMode:(SharedSkikoBlendMode *)blendMode paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawTriangles(positions:colors:texCoords:indices:blendMode:paint:)")));
- (SharedSkikoCanvas *)drawVerticesVertexMode:(SharedSkikoVertexMode *)vertexMode positions:(SharedKotlinFloatArray *)positions colors:(SharedKotlinIntArray * _Nullable)colors texCoords:(SharedKotlinFloatArray * _Nullable)texCoords indices:(SharedKotlinShortArray * _Nullable)indices blendMode:(SharedSkikoBlendMode *)blendMode paint:(SharedSkikoPaint *)paint __attribute__((swift_name("drawVertices(vertexMode:positions:colors:texCoords:indices:blendMode:paint:)")));
- (BOOL)readPixelsBitmap:(SharedSkikoBitmap *)bitmap srcX:(int32_t)srcX srcY:(int32_t)srcY __attribute__((swift_name("readPixels(bitmap:srcX:srcY:)")));
- (SharedSkikoCanvas *)resetMatrix __attribute__((swift_name("resetMatrix()")));
- (SharedSkikoCanvas *)restore __attribute__((swift_name("restore()")));
- (SharedSkikoCanvas *)restoreToCountSaveCount:(int32_t)saveCount __attribute__((swift_name("restoreToCount(saveCount:)")));
- (SharedSkikoCanvas *)rotateDeg:(float)deg __attribute__((swift_name("rotate(deg:)")));
- (SharedSkikoCanvas *)rotateDeg:(float)deg x:(float)x y:(float)y __attribute__((swift_name("rotate(deg:x:y:)")));
- (int32_t)save __attribute__((swift_name("save()")));
- (int32_t)saveLayerLayerRec:(SharedSkikoCanvasSaveLayerRec *)layerRec __attribute__((swift_name("saveLayer(layerRec:)")));
- (int32_t)saveLayerBounds:(SharedSkikoRect * _Nullable)bounds paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("saveLayer(bounds:paint:)")));
- (int32_t)saveLayerLeft:(float)left top:(float)top right:(float)right bottom:(float)bottom paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("saveLayer(left:top:right:bottom:paint:)")));
- (SharedSkikoCanvas *)scaleSx:(float)sx sy:(float)sy __attribute__((swift_name("scale(sx:sy:)")));
- (SharedSkikoCanvas *)setMatrixMatrix:(SharedSkikoMatrix33 *)matrix __attribute__((swift_name("setMatrix(matrix:)")));
- (SharedSkikoCanvas *)skewSx:(float)sx sy:(float)sy __attribute__((swift_name("skew(sx:sy:)")));
- (SharedSkikoCanvas *)translateDx:(float)dx dy:(float)dy __attribute__((swift_name("translate(dx:dy:)")));
- (BOOL)writePixelsBitmap:(SharedSkikoBitmap *)bitmap x:(int32_t)x y:(int32_t)y __attribute__((swift_name("writePixels(bitmap:x:y:)")));
@property (readonly) SharedSkikoMatrix44 *localToDevice __attribute__((swift_name("localToDevice")));
@property (readonly) SharedSkikoMatrix33 *localToDeviceAsMatrix33 __attribute__((swift_name("localToDeviceAsMatrix33")));
@property (readonly) int32_t saveCount __attribute__((swift_name("saveCount")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoContentChangeMode")))
@interface SharedSkikoContentChangeMode : SharedKotlinEnum<SharedSkikoContentChangeMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoContentChangeMode *discard __attribute__((swift_name("discard")));
@property (class, readonly) SharedSkikoContentChangeMode *retain_ __attribute__((swift_name("retain_")));
+ (SharedKotlinArray<SharedSkikoContentChangeMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoContentChangeMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBackendRenderTarget")))
@interface SharedSkikoBackendRenderTarget : SharedSkikoManaged
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoBackendRenderTargetCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoSurfaceOrigin")))
@interface SharedSkikoSurfaceOrigin : SharedKotlinEnum<SharedSkikoSurfaceOrigin *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoSurfaceOrigin *topLeft __attribute__((swift_name("topLeft")));
@property (class, readonly) SharedSkikoSurfaceOrigin *bottomLeft __attribute__((swift_name("bottomLeft")));
+ (SharedKotlinArray<SharedSkikoSurfaceOrigin *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoSurfaceOrigin *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoSurfaceColorFormat")))
@interface SharedSkikoSurfaceColorFormat : SharedKotlinEnum<SharedSkikoSurfaceColorFormat *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoSurfaceColorFormat *unknown __attribute__((swift_name("unknown")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *alpha8 __attribute__((swift_name("alpha8")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgb565 __attribute__((swift_name("rgb565")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *argb4444 __attribute__((swift_name("argb4444")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgba8888 __attribute__((swift_name("rgba8888")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgb888x __attribute__((swift_name("rgb888x")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *bgra8888 __attribute__((swift_name("bgra8888")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgba1010102 __attribute__((swift_name("rgba1010102")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgb101010x __attribute__((swift_name("rgb101010x")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *gray8 __attribute__((swift_name("gray8")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgbaF16Norm __attribute__((swift_name("rgbaF16Norm")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgbaF16 __attribute__((swift_name("rgbaF16")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *rgbaF32 __attribute__((swift_name("rgbaF32")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *r8g8Unorm __attribute__((swift_name("r8g8Unorm")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *a16Float __attribute__((swift_name("a16Float")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *r16g16Float __attribute__((swift_name("r16g16Float")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *a16Unorm __attribute__((swift_name("a16Unorm")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *r16g16Unorm __attribute__((swift_name("r16g16Unorm")));
@property (class, readonly) SharedSkikoSurfaceColorFormat *r16g16b16a16Unorm __attribute__((swift_name("r16g16b16a16Unorm")));
+ (SharedKotlinArray<SharedSkikoSurfaceColorFormat *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoSurfaceColorFormat *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoSurfaceProps")))
@interface SharedSkikoSurfaceProps : SharedBase
- (instancetype)initWithGeo:(SharedSkikoPixelGeometry *)geo __attribute__((swift_name("init(geo:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithIsDeviceIndependentFonts:(BOOL)isDeviceIndependentFonts pixelGeometry:(SharedSkikoPixelGeometry *)pixelGeometry __attribute__((swift_name("init(isDeviceIndependentFonts:pixelGeometry:)"))) __attribute__((objc_designated_initializer));
- (int32_t)_getFlags __attribute__((swift_name("_getFlags()")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoSurfaceProps *)withDeviceIndependentFonts_deviceIndependentFonts:(BOOL)_deviceIndependentFonts __attribute__((swift_name("withDeviceIndependentFonts(_deviceIndependentFonts:)")));
- (SharedSkikoSurfaceProps *)withPixelGeometry_pixelGeometry:(SharedSkikoPixelGeometry *)_pixelGeometry __attribute__((swift_name("withPixelGeometry(_pixelGeometry:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoCanvas.Companion")))
@interface SharedSkikoCanvasCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoCanvasCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoClipMode")))
@interface SharedSkikoClipMode : SharedKotlinEnum<SharedSkikoClipMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoClipMode *difference __attribute__((swift_name("difference")));
@property (class, readonly) SharedSkikoClipMode *intersect __attribute__((swift_name("intersect")));
+ (SharedKotlinArray<SharedSkikoClipMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoClipMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion")))
@interface SharedSkikoRegion : SharedSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoRegionCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)computeRegionComplexity __attribute__((swift_name("computeRegionComplexity()")));
- (BOOL)containsRect:(SharedSkikoIRect *)rect __attribute__((swift_name("contains(rect:)")));
- (BOOL)containsR:(SharedSkikoRegion * _Nullable)r __attribute__((swift_name("contains(r:)")));
- (BOOL)containsX:(int32_t)x y:(int32_t)y __attribute__((swift_name("contains(x:y:)")));
- (BOOL)getBoundaryPathP:(SharedSkikoPath * _Nullable)p __attribute__((swift_name("getBoundaryPath(p:)")));
- (BOOL)intersectsRect:(SharedSkikoIRect *)rect __attribute__((swift_name("intersects(rect:)")));
- (BOOL)intersectsR:(SharedSkikoRegion * _Nullable)r __attribute__((swift_name("intersects(r:)")));
- (BOOL)opRect:(SharedSkikoIRect *)rect op:(SharedSkikoRegionOp *)op __attribute__((swift_name("op(rect:op:)")));
- (BOOL)opR:(SharedSkikoRegion * _Nullable)r op:(SharedSkikoRegionOp *)op __attribute__((swift_name("op(r:op:)")));
- (BOOL)opRect:(SharedSkikoIRect *)rect r:(SharedSkikoRegion * _Nullable)r op:(SharedSkikoRegionOp *)op __attribute__((swift_name("op(rect:r:op:)")));
- (BOOL)opR:(SharedSkikoRegion * _Nullable)r rect:(SharedSkikoIRect *)rect op:(SharedSkikoRegionOp *)op __attribute__((swift_name("op(r:rect:op:)")));
- (BOOL)opA:(SharedSkikoRegion * _Nullable)a b:(SharedSkikoRegion * _Nullable)b op:(SharedSkikoRegionOp *)op __attribute__((swift_name("op(a:b:op:)")));
- (BOOL)quickContainsRect:(SharedSkikoIRect *)rect __attribute__((swift_name("quickContains(rect:)")));
- (BOOL)quickRejectRect:(SharedSkikoIRect *)rect __attribute__((swift_name("quickReject(rect:)")));
- (BOOL)quickRejectR:(SharedSkikoRegion * _Nullable)r __attribute__((swift_name("quickReject(r:)")));
- (BOOL)setR:(SharedSkikoRegion * _Nullable)r __attribute__((swift_name("set(r:)")));
- (BOOL)setEmpty __attribute__((swift_name("setEmpty()")));
- (BOOL)setPathPath:(SharedSkikoPath * _Nullable)path clip:(SharedSkikoRegion * _Nullable)clip __attribute__((swift_name("setPath(path:clip:)")));
- (BOOL)setRectRect:(SharedSkikoIRect *)rect __attribute__((swift_name("setRect(rect:)")));
- (BOOL)setRectsRects:(SharedKotlinArray<SharedSkikoIRect *> *)rects __attribute__((swift_name("setRects(rects:)")));
- (BOOL)setRegionR:(SharedSkikoRegion * _Nullable)r __attribute__((swift_name("setRegion(r:)")));
- (void)translateDx:(int32_t)dx dy:(int32_t)dy __attribute__((swift_name("translate(dx:dy:)")));
@property (readonly) SharedSkikoIRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) BOOL isComplex __attribute__((swift_name("isComplex")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) BOOL isRect __attribute__((swift_name("isRect")));
@end

__attribute__((swift_name("SkikoDrawable")))
@interface SharedSkikoDrawable : SharedSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoDrawableCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoDrawable *)drawCanvas:(SharedSkikoCanvas * _Nullable)canvas __attribute__((swift_name("draw(canvas:)")));
- (SharedSkikoDrawable *)drawCanvas:(SharedSkikoCanvas * _Nullable)canvas matrix:(SharedSkikoMatrix33 * _Nullable)matrix __attribute__((swift_name("draw(canvas:matrix:)")));
- (SharedSkikoDrawable *)drawCanvas:(SharedSkikoCanvas * _Nullable)canvas x:(float)x y:(float)y __attribute__((swift_name("draw(canvas:x:y:)")));
- (SharedSkikoPicture *)makePictureSnapshot __attribute__((swift_name("makePictureSnapshot()")));
- (SharedSkikoDrawable *)notifyDrawingChanged __attribute__((swift_name("notifyDrawingChanged()")));
- (void)onDrawCanvas:(SharedSkikoCanvas * _Nullable)canvas __attribute__((swift_name("onDraw(canvas:)")));
- (SharedSkikoRect *)onGetBounds __attribute__((swift_name("onGetBounds()")));
@property (readonly) SharedSkikoRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) int32_t generationId __attribute__((swift_name("generationId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFilterMode")))
@interface SharedSkikoFilterMode : SharedKotlinEnum<SharedSkikoFilterMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoFilterMode *nearest __attribute__((swift_name("nearest")));
@property (class, readonly) SharedSkikoFilterMode *linear __attribute__((swift_name("linear")));
+ (SharedKotlinArray<SharedSkikoFilterMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoFilterMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPicture")))
@interface SharedSkikoPicture : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoPictureCompanion *companion __attribute__((swift_name("companion")));
- (SharedSkikoShader *)makeShaderTmx:(SharedSkikoFilterTileMode *)tmx tmy:(SharedSkikoFilterTileMode *)tmy mode:(SharedSkikoFilterMode *)mode localMatrix:(SharedSkikoMatrix33 * _Nullable)localMatrix tileRect:(SharedSkikoRect * _Nullable)tileRect __attribute__((swift_name("makeShader(tmx:tmy:mode:localMatrix:tileRect:)")));
- (SharedSkikoPicture *)playbackCanvas:(SharedSkikoCanvas * _Nullable)canvas abort:(SharedBoolean *(^ _Nullable)(void))abort __attribute__((swift_name("playback(canvas:abort:)")));
- (SharedSkikoData *)serializeToData __attribute__((swift_name("serializeToData()")));
@property (readonly) void * _Nullable approximateBytesUsed __attribute__((swift_name("approximateBytesUsed")));
@property (readonly) int32_t approximateOpCount __attribute__((swift_name("approximateOpCount")));
@property (readonly) SharedSkikoRect *cullRect __attribute__((swift_name("cullRect")));
@property (readonly) int32_t uniqueId __attribute__((swift_name("uniqueId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFont")))
@interface SharedSkikoFont : SharedSkikoManaged
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithTypeface:(SharedSkikoTypeface * _Nullable)typeface __attribute__((swift_name("init(typeface:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTypeface:(SharedSkikoTypeface * _Nullable)typeface size:(float)size __attribute__((swift_name("init(typeface:size:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTypeface:(SharedSkikoTypeface * _Nullable)typeface size:(float)size scaleX:(float)scaleX skewX:(float)skewX __attribute__((swift_name("init(typeface:size:scaleX:skewX:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoFontCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)areBitmapsEmbedded __attribute__((swift_name("areBitmapsEmbedded()")));
- (SharedKotlinArray<SharedSkikoRect *> *)getBoundsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs __attribute__((swift_name("getBounds(glyphs:)")));
- (SharedKotlinArray<SharedSkikoRect *> *)getBoundsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs p:(SharedSkikoPaint * _Nullable)p __attribute__((swift_name("getBounds(glyphs:p:)")));
- (SharedSkikoPath * _Nullable)getPathGlyph:(int16_t)glyph __attribute__((swift_name("getPath(glyph:)")));
- (SharedKotlinArray<SharedSkikoPath *> *)getPathsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs __attribute__((swift_name("getPaths(glyphs:)")));
- (SharedKotlinArray<SharedSkikoPoint *> *)getPositionsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs __attribute__((swift_name("getPositions(glyphs:)")));
- (SharedKotlinArray<SharedSkikoPoint *> *)getPositionsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs offset:(SharedSkikoPoint *)offset __attribute__((swift_name("getPositions(glyphs:offset:)")));
- (SharedKotlinShortArray *)getStringGlyphsS:(NSString *)s __attribute__((swift_name("getStringGlyphs(s:)")));
- (int32_t)getStringGlyphsCountS:(NSString * _Nullable)s __attribute__((swift_name("getStringGlyphsCount(s:)")));
- (int16_t)getUTF32GlyphUnichar:(int32_t)unichar __attribute__((swift_name("getUTF32Glyph(unichar:)")));
- (SharedKotlinShortArray *)getUTF32GlyphsUni:(SharedKotlinIntArray * _Nullable)uni __attribute__((swift_name("getUTF32Glyphs(uni:)")));
- (SharedKotlinFloatArray *)getWidthsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs __attribute__((swift_name("getWidths(glyphs:)")));
- (SharedKotlinFloatArray *)getXPositionsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs __attribute__((swift_name("getXPositions(glyphs:)")));
- (SharedKotlinFloatArray *)getXPositionsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs offset:(float)offset __attribute__((swift_name("getXPositions(glyphs:offset:)")));
- (SharedSkikoFont *)makeWithSizeSize:(float)size __attribute__((swift_name("makeWithSize(size:)")));
- (SharedSkikoRect *)measureTextS:(NSString * _Nullable)s p:(SharedSkikoPaint * _Nullable)p __attribute__((swift_name("measureText(s:p:)")));
- (float)measureTextWidthS:(NSString * _Nullable)s __attribute__((swift_name("measureTextWidth(s:)")));
- (float)measureTextWidthS:(NSString * _Nullable)s p:(SharedSkikoPaint * _Nullable)p __attribute__((swift_name("measureTextWidth(s:p:)")));
- (void)setBitmapsEmbeddedValue:(BOOL)value __attribute__((swift_name("setBitmapsEmbedded(value:)")));
- (SharedSkikoFont *)setTypefaceTypeface:(SharedSkikoTypeface * _Nullable)typeface __attribute__((swift_name("setTypeface(typeface:)")));
@property SharedSkikoFontEdging *edging __attribute__((swift_name("edging")));
@property SharedSkikoFontHinting *hinting __attribute__((swift_name("hinting")));
@property BOOL isAutoHintingForced __attribute__((swift_name("isAutoHintingForced")));
@property BOOL isBaselineSnapped __attribute__((swift_name("isBaselineSnapped")));
@property BOOL isEmboldened __attribute__((swift_name("isEmboldened")));
@property BOOL isLinearMetrics __attribute__((swift_name("isLinearMetrics")));
@property BOOL isSubpixel __attribute__((swift_name("isSubpixel")));
@property (readonly) SharedSkikoFontMetrics *metrics __attribute__((swift_name("metrics")));
@property float scaleX __attribute__((swift_name("scaleX")));
@property float size __attribute__((swift_name("size")));
@property float skewX __attribute__((swift_name("skewX")));
@property (readonly) float spacing __attribute__((swift_name("spacing")));
@property (readonly) SharedSkikoTypeface * _Nullable typeface __attribute__((swift_name("typeface")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoTextBlob")))
@interface SharedSkikoTextBlob : SharedSkikoManaged <SharedKotlinIterable>
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoTextBlobCompanion *companion __attribute__((swift_name("companion")));
- (SharedKotlinFloatArray * _Nullable)getInterceptsLowerBound:(float)lowerBound upperBound:(float)upperBound __attribute__((swift_name("getIntercepts(lowerBound:upperBound:)")));
- (SharedKotlinFloatArray * _Nullable)getInterceptsLowerBound:(float)lowerBound upperBound:(float)upperBound paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("getIntercepts(lowerBound:upperBound:paint:)")));
- (id<SharedKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (SharedSkikoData *)serializeToData __attribute__((swift_name("serializeToData()")));
@property (readonly) SharedSkikoRect *blockBounds __attribute__((swift_name("blockBounds")));
@property (readonly) SharedSkikoRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) SharedKotlinIntArray *clusters __attribute__((swift_name("clusters")));
@property (readonly) float firstBaseline __attribute__((swift_name("firstBaseline")));
@property (readonly) SharedKotlinShortArray *glyphs __attribute__((swift_name("glyphs")));
@property (readonly) float lastBaseline __attribute__((swift_name("lastBaseline")));
@property (readonly) SharedKotlinFloatArray *positions __attribute__((swift_name("positions")));
@property (readonly) SharedSkikoRect *tightBounds __attribute__((swift_name("tightBounds")));
@property (readonly) int32_t uniqueId __attribute__((swift_name("uniqueId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoTextLine")))
@interface SharedSkikoTextLine : SharedSkikoManaged
- (instancetype)initWithPtr:(void * _Nullable)ptr finalizer:(void * _Nullable)finalizer managed:(BOOL)managed __attribute__((swift_name("init(ptr:finalizer:managed:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoTextLineCompanion *companion __attribute__((swift_name("companion")));
- (float)getCoordAtOffsetOffset:(int32_t)offset __attribute__((swift_name("getCoordAtOffset(offset:)")));
- (SharedKotlinFloatArray * _Nullable)getInterceptsLowerBound:(float)lowerBound upperBound:(float)upperBound __attribute__((swift_name("getIntercepts(lowerBound:upperBound:)")));
- (SharedKotlinFloatArray * _Nullable)getInterceptsLowerBound:(float)lowerBound upperBound:(float)upperBound paint:(SharedSkikoPaint * _Nullable)paint __attribute__((swift_name("getIntercepts(lowerBound:upperBound:paint:)")));
- (int32_t)getLeftOffsetAtCoordX:(float)x __attribute__((swift_name("getLeftOffsetAtCoord(x:)")));
- (int32_t)getOffsetAtCoordX:(float)x __attribute__((swift_name("getOffsetAtCoord(x:)")));
@property (readonly) float ascent __attribute__((swift_name("ascent")));
@property (readonly) float capHeight __attribute__((swift_name("capHeight")));
@property (readonly) float descent __attribute__((swift_name("descent")));
@property (readonly) SharedKotlinShortArray *glyphs __attribute__((swift_name("glyphs")));
@property (readonly) float height __attribute__((swift_name("height")));
@property (readonly) float leading __attribute__((swift_name("leading")));
@property (readonly) SharedKotlinFloatArray *positions __attribute__((swift_name("positions")));
@property (readonly) SharedSkikoTextBlob * _Nullable textBlob __attribute__((swift_name("textBlob")));
@property (readonly) float width __attribute__((swift_name("width")));
@property (readonly) float xHeight __attribute__((swift_name("xHeight")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinShortArray")))
@interface SharedKotlinShortArray : SharedBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(SharedShort *(^)(SharedInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int16_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (SharedKotlinShortIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int16_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoVertexMode")))
@interface SharedSkikoVertexMode : SharedKotlinEnum<SharedSkikoVertexMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoVertexMode *triangles __attribute__((swift_name("triangles")));
@property (class, readonly) SharedSkikoVertexMode *triangleStrip __attribute__((swift_name("triangleStrip")));
@property (class, readonly) SharedSkikoVertexMode *triangleFan __attribute__((swift_name("triangleFan")));
+ (SharedKotlinArray<SharedSkikoVertexMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoVertexMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoCanvas.SaveLayerRec")))
@interface SharedSkikoCanvasSaveLayerRec : SharedBase
- (instancetype)initWithBounds:(SharedSkikoRect * _Nullable)bounds paint:(SharedSkikoPaint * _Nullable)paint backdrop:(SharedSkikoImageFilter * _Nullable)backdrop colorSpace:(SharedSkikoColorSpace * _Nullable)colorSpace saveLayerFlags:(SharedSkikoCanvasSaveLayerFlags *)saveLayerFlags __attribute__((swift_name("init(bounds:paint:backdrop:colorSpace:saveLayerFlags:)"))) __attribute__((objc_designated_initializer));
@property (readonly) SharedSkikoImageFilter * _Nullable backdrop __attribute__((swift_name("backdrop")));
@property (readonly) SharedSkikoRect * _Nullable bounds __attribute__((swift_name("bounds")));
@property (readonly) SharedSkikoColorSpace * _Nullable colorSpace __attribute__((swift_name("colorSpace")));
@property (readonly) SharedSkikoPaint * _Nullable paint __attribute__((swift_name("paint")));
@property (readonly) SharedSkikoCanvasSaveLayerFlags *saveLayerFlags __attribute__((swift_name("saveLayerFlags")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoBackendRenderTarget.Companion")))
@interface SharedSkikoBackendRenderTargetCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoBackendRenderTargetCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoBackendRenderTarget *)makeDirect3DWidth:(int32_t)width height:(int32_t)height texturePtr:(void * _Nullable)texturePtr format:(int32_t)format sampleCnt:(int32_t)sampleCnt levelCnt:(int32_t)levelCnt __attribute__((swift_name("makeDirect3D(width:height:texturePtr:format:sampleCnt:levelCnt:)")));
- (SharedSkikoBackendRenderTarget *)makeGLWidth:(int32_t)width height:(int32_t)height sampleCnt:(int32_t)sampleCnt stencilBits:(int32_t)stencilBits fbId:(int32_t)fbId fbFormat:(int32_t)fbFormat __attribute__((swift_name("makeGL(width:height:sampleCnt:stencilBits:fbId:fbFormat:)")));
- (SharedSkikoBackendRenderTarget *)makeMetalWidth:(int32_t)width height:(int32_t)height texturePtr:(void * _Nullable)texturePtr __attribute__((swift_name("makeMetal(width:height:texturePtr:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPixelGeometry")))
@interface SharedSkikoPixelGeometry : SharedKotlinEnum<SharedSkikoPixelGeometry *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoPixelGeometry *unknown __attribute__((swift_name("unknown")));
@property (class, readonly) SharedSkikoPixelGeometry *rgbH __attribute__((swift_name("rgbH")));
@property (class, readonly) SharedSkikoPixelGeometry *bgrH __attribute__((swift_name("bgrH")));
@property (class, readonly) SharedSkikoPixelGeometry *rgbV __attribute__((swift_name("rgbV")));
@property (class, readonly) SharedSkikoPixelGeometry *bgrV __attribute__((swift_name("bgrV")));
+ (SharedKotlinArray<SharedSkikoPixelGeometry *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoPixelGeometry *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion.Companion")))
@interface SharedSkikoRegionCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRegionCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion.Op")))
@interface SharedSkikoRegionOp : SharedKotlinEnum<SharedSkikoRegionOp *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoRegionOpCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) SharedSkikoRegionOp *difference __attribute__((swift_name("difference")));
@property (class, readonly) SharedSkikoRegionOp *intersect __attribute__((swift_name("intersect")));
@property (class, readonly) SharedSkikoRegionOp *union_ __attribute__((swift_name("union_")));
@property (class, readonly) SharedSkikoRegionOp *xor_ __attribute__((swift_name("xor_")));
@property (class, readonly) SharedSkikoRegionOp *reverseDifference __attribute__((swift_name("reverseDifference")));
@property (class, readonly) SharedSkikoRegionOp *replace __attribute__((swift_name("replace")));
+ (SharedKotlinArray<SharedSkikoRegionOp *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoRegionOp *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoDrawable.Companion")))
@interface SharedSkikoDrawableCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoDrawableCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPicture.Companion")))
@interface SharedSkikoPictureCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoPictureCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoPicture * _Nullable)makeFromDataData:(SharedSkikoData * _Nullable)data __attribute__((swift_name("makeFromData(data:)")));
- (SharedSkikoPicture *)makePlaceholderCull:(SharedSkikoRect *)cull __attribute__((swift_name("makePlaceholder(cull:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoTypeface")))
@interface SharedSkikoTypeface : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoTypefaceCompanion *companion __attribute__((swift_name("companion")));
- (SharedKotlinIntArray * _Nullable)getKerningPairAdjustmentsGlyphs:(SharedKotlinShortArray * _Nullable)glyphs __attribute__((swift_name("getKerningPairAdjustments(glyphs:)")));
- (SharedKotlinShortArray *)getStringGlyphsS:(NSString *)s __attribute__((swift_name("getStringGlyphs(s:)")));
- (SharedSkikoData * _Nullable)getTableDataTag:(NSString *)tag __attribute__((swift_name("getTableData(tag:)")));
- (void * _Nullable)getTableSizeTag:(NSString *)tag __attribute__((swift_name("getTableSize(tag:)")));
- (int16_t)getUTF32GlyphUnichar:(int32_t)unichar __attribute__((swift_name("getUTF32Glyph(unichar:)")));
- (SharedKotlinShortArray *)getUTF32GlyphsUni:(SharedKotlinIntArray * _Nullable)uni __attribute__((swift_name("getUTF32Glyphs(uni:)")));
- (SharedSkikoTypeface *)makeCloneVariation:(SharedSkikoFontVariation *)variation __attribute__((swift_name("makeClone(variation:)")));
- (SharedSkikoTypeface *)makeCloneVariations:(SharedKotlinArray<SharedSkikoFontVariation *> *)variations collectionIndex:(int32_t)collectionIndex __attribute__((swift_name("makeClone(variations:collectionIndex:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedSkikoRect *bounds __attribute__((swift_name("bounds")));
@property (readonly) NSString *familyName __attribute__((swift_name("familyName")));
@property (readonly) SharedKotlinArray<SharedSkikoFontFamilyName *> *familyNames __attribute__((swift_name("familyNames")));
@property (readonly) SharedSkikoFontStyle *fontStyle __attribute__((swift_name("fontStyle")));
@property (readonly) int32_t glyphsCount __attribute__((swift_name("glyphsCount")));
@property (readonly) BOOL isBold __attribute__((swift_name("isBold")));
@property (readonly) BOOL isFixedPitch __attribute__((swift_name("isFixedPitch")));
@property (readonly) BOOL isItalic __attribute__((swift_name("isItalic")));
@property (readonly) SharedKotlinArray<NSString *> *tableTags __attribute__((swift_name("tableTags")));
@property (readonly) int32_t tablesCount __attribute__((swift_name("tablesCount")));
@property (readonly) int32_t uniqueId __attribute__((swift_name("uniqueId")));
@property (readonly) int32_t unitsPerEm __attribute__((swift_name("unitsPerEm")));
@property (readonly) SharedKotlinArray<SharedSkikoFontVariationAxis *> * _Nullable variationAxes __attribute__((swift_name("variationAxes")));
@property (readonly) SharedKotlinArray<SharedSkikoFontVariation *> * _Nullable variations __attribute__((swift_name("variations")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFont.Companion")))
@interface SharedSkikoFontCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontEdging")))
@interface SharedSkikoFontEdging : SharedKotlinEnum<SharedSkikoFontEdging *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoFontEdging *alias __attribute__((swift_name("alias")));
@property (class, readonly) SharedSkikoFontEdging *antiAlias __attribute__((swift_name("antiAlias")));
@property (class, readonly) SharedSkikoFontEdging *subpixelAntiAlias __attribute__((swift_name("subpixelAntiAlias")));
+ (SharedKotlinArray<SharedSkikoFontEdging *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoFontEdging *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontHinting")))
@interface SharedSkikoFontHinting : SharedKotlinEnum<SharedSkikoFontHinting *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoFontHinting *none __attribute__((swift_name("none")));
@property (class, readonly) SharedSkikoFontHinting *slight __attribute__((swift_name("slight")));
@property (class, readonly) SharedSkikoFontHinting *normal __attribute__((swift_name("normal")));
@property (class, readonly) SharedSkikoFontHinting *full __attribute__((swift_name("full")));
+ (SharedKotlinArray<SharedSkikoFontHinting *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoFontHinting *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontMetrics")))
@interface SharedSkikoFontMetrics : SharedBase
- (instancetype)initWithTop:(float)top ascent:(float)ascent descent:(float)descent bottom:(float)bottom leading:(float)leading avgCharWidth:(float)avgCharWidth maxCharWidth:(float)maxCharWidth xMin:(float)xMin xMax:(float)xMax xHeight:(float)xHeight capHeight:(float)capHeight underlineThickness:(SharedFloat * _Nullable)underlineThickness underlinePosition:(SharedFloat * _Nullable)underlinePosition strikeoutThickness:(SharedFloat * _Nullable)strikeoutThickness strikeoutPosition:(SharedFloat * _Nullable)strikeoutPosition __attribute__((swift_name("init(top:ascent:descent:bottom:leading:avgCharWidth:maxCharWidth:xMin:xMax:xHeight:capHeight:underlineThickness:underlinePosition:strikeoutThickness:strikeoutPosition:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoFontMetricsCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) float ascent __attribute__((swift_name("ascent")));
@property (readonly) float avgCharWidth __attribute__((swift_name("avgCharWidth")));
@property (readonly) float bottom __attribute__((swift_name("bottom")));
@property (readonly) float capHeight __attribute__((swift_name("capHeight")));
@property (readonly) float descent __attribute__((swift_name("descent")));
@property (readonly) float height __attribute__((swift_name("height")));
@property (readonly) float leading __attribute__((swift_name("leading")));
@property (readonly) float maxCharWidth __attribute__((swift_name("maxCharWidth")));
@property (readonly) SharedFloat * _Nullable strikeoutPosition __attribute__((swift_name("strikeoutPosition")));
@property (readonly) SharedFloat * _Nullable strikeoutThickness __attribute__((swift_name("strikeoutThickness")));
@property (readonly) float top __attribute__((swift_name("top")));
@property (readonly) SharedFloat * _Nullable underlinePosition __attribute__((swift_name("underlinePosition")));
@property (readonly) SharedFloat * _Nullable underlineThickness __attribute__((swift_name("underlineThickness")));
@property (readonly) float xHeight __attribute__((swift_name("xHeight")));
@property (readonly) float xMax __attribute__((swift_name("xMax")));
@property (readonly) float xMin __attribute__((swift_name("xMin")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoTextBlob.Companion")))
@interface SharedSkikoTextBlobCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoTextBlobCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoTextBlob * _Nullable)makeFromDataData:(SharedSkikoData * _Nullable)data __attribute__((swift_name("makeFromData(data:)")));
- (SharedSkikoTextBlob * _Nullable)makeFromPosGlyphs:(SharedKotlinShortArray *)glyphs pos:(SharedKotlinArray<SharedSkikoPoint *> *)pos font:(SharedSkikoFont * _Nullable)font __attribute__((swift_name("makeFromPos(glyphs:pos:font:)")));
- (SharedSkikoTextBlob * _Nullable)makeFromPosHGlyphs:(SharedKotlinShortArray *)glyphs xpos:(SharedKotlinFloatArray *)xpos ypos:(float)ypos font:(SharedSkikoFont * _Nullable)font __attribute__((swift_name("makeFromPosH(glyphs:xpos:ypos:font:)")));
- (SharedSkikoTextBlob * _Nullable)makeFromRSXformGlyphs:(SharedKotlinShortArray *)glyphs xform:(SharedKotlinArray<SharedSkikoRSXform *> *)xform font:(SharedSkikoFont * _Nullable)font __attribute__((swift_name("makeFromRSXform(glyphs:xform:font:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoTextLine.Companion")))
@interface SharedSkikoTextLineCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoTextLineCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoTextLine *)makeText:(NSString * _Nullable)text font:(SharedSkikoFont * _Nullable)font __attribute__((swift_name("make(text:font:)")));
- (SharedSkikoTextLine *)makeText:(NSString * _Nullable)text font:(SharedSkikoFont * _Nullable)font opts:(SharedSkikoShapingOptions * _Nullable)opts __attribute__((swift_name("make(text:font:opts:)")));
@end

__attribute__((swift_name("KotlinShortIterator")))
@interface SharedKotlinShortIterator : SharedBase <SharedKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (SharedShort *)next __attribute__((swift_name("next()")));
- (int16_t)nextShort __attribute__((swift_name("nextShort()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoCanvas.SaveLayerFlags")))
@interface SharedSkikoCanvasSaveLayerFlags : SharedBase
- (instancetype)initWithFlagsSet:(SharedKotlinArray<SharedSkikoCanvasSaveLayerFlagsSet *> *)flagsSet __attribute__((swift_name("init(flagsSet:)"))) __attribute__((objc_designated_initializer));
- (BOOL)containsFlag:(SharedSkikoCanvasSaveLayerFlagsSet *)flag __attribute__((swift_name("contains(flag:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRegion.OpCompanion")))
@interface SharedSkikoRegionOpCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRegionOpCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoTypeface.Companion")))
@interface SharedSkikoTypefaceCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoTypefaceCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoTypeface *)makeEmpty __attribute__((swift_name("makeEmpty()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontVariation")))
@interface SharedSkikoFontVariation : SharedBase
- (instancetype)initWith_tag:(int32_t)_tag value:(float)value __attribute__((swift_name("init(_tag:value:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithFeature:(NSString *)feature value:(float)value __attribute__((swift_name("init(feature:value:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoFontVariationCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t _tag __attribute__((swift_name("_tag")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@property (readonly) float value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontFamilyName")))
@interface SharedSkikoFontFamilyName : SharedBase
- (instancetype)initWithName:(NSString *)name language:(NSString *)language __attribute__((swift_name("init(name:language:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *language __attribute__((swift_name("language")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontStyle")))
@interface SharedSkikoFontStyle : SharedBase
- (instancetype)initWithWeight:(int32_t)weight width:(int32_t)width slant:(SharedSkikoFontSlant *)slant __attribute__((swift_name("init(weight:width:slant:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoFontStyleCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoFontStyle *)withSlantSlant:(SharedSkikoFontSlant *)slant __attribute__((swift_name("withSlant(slant:)")));
- (SharedSkikoFontStyle *)withWeightWeight:(int32_t)weight __attribute__((swift_name("withWeight(weight:)")));
- (SharedSkikoFontStyle *)withWidthWidth:(int32_t)width __attribute__((swift_name("withWidth(width:)")));
@property (readonly) int32_t _value __attribute__((swift_name("_value")));
@property (readonly) SharedSkikoFontSlant *slant __attribute__((swift_name("slant")));
@property (readonly) int32_t weight __attribute__((swift_name("weight")));
@property (readonly) int32_t width __attribute__((swift_name("width")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontVariationAxis")))
@interface SharedSkikoFontVariationAxis : SharedBase
- (instancetype)initWithTag:(NSString *)tag min:(float)min def:(float)def max:(float)max __attribute__((swift_name("init(tag:min:def:max:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWith_tag:(int32_t)_tag minValue:(float)minValue defaultValue:(float)defaultValue maxValue:(float)maxValue isHidden:(BOOL)isHidden __attribute__((swift_name("init(_tag:minValue:defaultValue:maxValue:isHidden:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithTag:(NSString *)tag min:(float)min def:(float)def max:(float)max hidden:(BOOL)hidden __attribute__((swift_name("init(tag:min:def:max:hidden:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t _tag __attribute__((swift_name("_tag")));
@property (readonly) float defaultValue __attribute__((swift_name("defaultValue")));
@property (readonly) BOOL isHidden __attribute__((swift_name("isHidden")));
@property (readonly) float maxValue __attribute__((swift_name("maxValue")));
@property (readonly) float minValue __attribute__((swift_name("minValue")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontMetrics.Companion")))
@interface SharedSkikoFontMetricsCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontMetricsCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRSXform")))
@interface SharedSkikoRSXform : SharedBase
- (instancetype)initWithScos:(float)scos ssin:(float)ssin tx:(float)tx ty:(float)ty __attribute__((swift_name("init(scos:ssin:tx:ty:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoRSXformCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoShapingOptions")))
@interface SharedSkikoShapingOptions : SharedBase
- (instancetype)initWithFontMgr:(SharedSkikoFontMgr * _Nullable)fontMgr features:(SharedKotlinArray<SharedSkikoFontFeature *> * _Nullable)features isLeftToRight:(BOOL)isLeftToRight isApproximateSpaces:(BOOL)isApproximateSpaces isApproximatePunctuation:(BOOL)isApproximatePunctuation __attribute__((swift_name("init(fontMgr:features:isLeftToRight:isApproximateSpaces:isApproximatePunctuation:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoShapingOptionsCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
- (SharedSkikoShapingOptions *)withApproximatePunctuation_approximatePunctuation:(BOOL)_approximatePunctuation __attribute__((swift_name("withApproximatePunctuation(_approximatePunctuation:)")));
- (SharedSkikoShapingOptions *)withApproximateSpaces_approximateSpaces:(BOOL)_approximateSpaces __attribute__((swift_name("withApproximateSpaces(_approximateSpaces:)")));
- (SharedSkikoShapingOptions *)withFeaturesFeatures:(SharedKotlinArray<SharedSkikoFontFeature *> * _Nullable)features __attribute__((swift_name("withFeatures(features:)")));
- (SharedSkikoShapingOptions *)withFeaturesFeaturesString:(NSString * _Nullable)featuresString __attribute__((swift_name("withFeatures(featuresString:)")));
- (SharedSkikoShapingOptions *)withFontMgr_fontMgr:(SharedSkikoFontMgr * _Nullable)_fontMgr __attribute__((swift_name("withFontMgr(_fontMgr:)")));
- (SharedSkikoShapingOptions *)withLeftToRight_leftToRight:(BOOL)_leftToRight __attribute__((swift_name("withLeftToRight(_leftToRight:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoCanvas.SaveLayerFlagsSet")))
@interface SharedSkikoCanvasSaveLayerFlagsSet : SharedKotlinEnum<SharedSkikoCanvasSaveLayerFlagsSet *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoCanvasSaveLayerFlagsSet *preservelcdtext __attribute__((swift_name("preservelcdtext")));
@property (class, readonly) SharedSkikoCanvasSaveLayerFlagsSet *initwithprevious __attribute__((swift_name("initwithprevious")));
@property (class, readonly) SharedSkikoCanvasSaveLayerFlagsSet *f16colortype __attribute__((swift_name("f16colortype")));
+ (SharedKotlinArray<SharedSkikoCanvasSaveLayerFlagsSet *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoCanvasSaveLayerFlagsSet *> *entries __attribute__((swift_name("entries")));
@property (readonly) int32_t mask __attribute__((swift_name("mask")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontVariation.Companion")))
@interface SharedSkikoFontVariationCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontVariationCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinArray<SharedSkikoFontVariation *> *)parseStr:(NSString *)str __attribute__((swift_name("parse(str:)")));
- (SharedSkikoFontVariation *)parseOneS:(NSString *)s __attribute__((swift_name("parseOne(s:)")));
@property (readonly) SharedKotlinArray<SharedSkikoFontVariation *> *EMPTY __attribute__((swift_name("EMPTY")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontSlant")))
@interface SharedSkikoFontSlant : SharedKotlinEnum<SharedSkikoFontSlant *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedSkikoFontSlant *upright __attribute__((swift_name("upright")));
@property (class, readonly) SharedSkikoFontSlant *italic __attribute__((swift_name("italic")));
@property (class, readonly) SharedSkikoFontSlant *oblique __attribute__((swift_name("oblique")));
+ (SharedKotlinArray<SharedSkikoFontSlant *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedSkikoFontSlant *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontStyle.Companion")))
@interface SharedSkikoFontStyleCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontStyleCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoFontStyle *BOLD __attribute__((swift_name("BOLD")));
@property (readonly) SharedSkikoFontStyle *BOLD_ITALIC __attribute__((swift_name("BOLD_ITALIC")));
@property (readonly) SharedSkikoFontStyle *ITALIC __attribute__((swift_name("ITALIC")));
@property (readonly) SharedSkikoFontStyle *NORMAL __attribute__((swift_name("NORMAL")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoRSXform.Companion")))
@interface SharedSkikoRSXformCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoRSXformCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoRSXform *)makeFromRadiansScale:(float)scale radians:(float)radians tx:(float)tx ty:(float)ty ax:(float)ax ay:(float)ay __attribute__((swift_name("makeFromRadians(scale:radians:tx:ty:ax:ay:)")));
@end

__attribute__((swift_name("SkikoFontMgr")))
@interface SharedSkikoFontMgr : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoFontMgrCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)getFamilyNameIndex:(int32_t)index __attribute__((swift_name("getFamilyName(index:)")));
- (SharedSkikoTypeface * _Nullable)legacyMakeTypefaceName:(NSString *)name style:(SharedSkikoFontStyle *)style __attribute__((swift_name("legacyMakeTypeface(name:style:)")));
- (SharedSkikoTypeface * _Nullable)makeFromDataData:(SharedSkikoData * _Nullable)data ttcIndex:(int32_t)ttcIndex __attribute__((swift_name("makeFromData(data:ttcIndex:)")));
- (SharedSkikoTypeface * _Nullable)makeFromFilePath:(NSString *)path ttcIndex:(int32_t)ttcIndex __attribute__((swift_name("makeFromFile(path:ttcIndex:)")));
- (SharedSkikoFontStyleSet * _Nullable)makeStyleSetIndex:(int32_t)index __attribute__((swift_name("makeStyleSet(index:)")));
- (SharedSkikoTypeface * _Nullable)matchFamiliesStyleFamilies:(SharedKotlinArray<NSString *> *)families style:(SharedSkikoFontStyle *)style __attribute__((swift_name("matchFamiliesStyle(families:style:)")));
- (SharedSkikoTypeface * _Nullable)matchFamiliesStyleCharacterFamilies:(SharedKotlinArray<NSString *> *)families style:(SharedSkikoFontStyle *)style bcp47:(SharedKotlinArray<NSString *> * _Nullable)bcp47 character:(int32_t)character __attribute__((swift_name("matchFamiliesStyleCharacter(families:style:bcp47:character:)")));
- (SharedSkikoFontStyleSet *)matchFamilyFamilyName:(NSString * _Nullable)familyName __attribute__((swift_name("matchFamily(familyName:)")));
- (SharedSkikoTypeface * _Nullable)matchFamilyStyleFamilyName:(NSString * _Nullable)familyName style:(SharedSkikoFontStyle *)style __attribute__((swift_name("matchFamilyStyle(familyName:style:)")));
- (SharedSkikoTypeface * _Nullable)matchFamilyStyleCharacterFamilyName:(NSString * _Nullable)familyName style:(SharedSkikoFontStyle *)style bcp47:(SharedKotlinArray<NSString *> * _Nullable)bcp47 character:(int32_t)character __attribute__((swift_name("matchFamilyStyleCharacter(familyName:style:bcp47:character:)")));
@property (readonly) int32_t familiesCount __attribute__((swift_name("familiesCount")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontFeature")))
@interface SharedSkikoFontFeature : SharedBase
- (instancetype)initWithFeature:(NSString *)feature __attribute__((swift_name("init(feature:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithFeature:(NSString *)feature value:(BOOL)value __attribute__((swift_name("init(feature:value:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithFeature:(NSString *)feature value_:(int32_t)value __attribute__((swift_name("init(feature:value_:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWith_tag:(int32_t)_tag value:(int32_t)value start:(uint32_t)start end:(uint32_t)end __attribute__((swift_name("init(_tag:value:start:end:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithFeature:(NSString *)feature value:(int32_t)value start:(uint32_t)start end:(uint32_t)end __attribute__((swift_name("init(feature:value:start:end:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedSkikoFontFeatureCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t _tag __attribute__((swift_name("_tag")));
@property (readonly) uint32_t end __attribute__((swift_name("end")));
@property (readonly) uint32_t start __attribute__((swift_name("start")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoShapingOptions.Companion")))
@interface SharedSkikoShapingOptionsCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoShapingOptionsCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedSkikoShapingOptions *DEFAULT __attribute__((swift_name("DEFAULT")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontMgr.Companion")))
@interface SharedSkikoFontMgrCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontMgrCompanion *shared __attribute__((swift_name("shared")));
@property (readonly, getter=default) SharedSkikoFontMgr *default_ __attribute__((swift_name("default_")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontStyleSet")))
@interface SharedSkikoFontStyleSet : SharedSkikoRefCnt

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr __attribute__((swift_name("init(ptr:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithPtr:(void * _Nullable)ptr allowClose:(BOOL)allowClose __attribute__((swift_name("init(ptr:allowClose:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SharedSkikoFontStyleSetCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)count __attribute__((swift_name("count()")));
- (SharedSkikoFontStyle *)getStyleIndex:(int32_t)index __attribute__((swift_name("getStyle(index:)")));
- (NSString *)getStyleNameIndex:(int32_t)index __attribute__((swift_name("getStyleName(index:)")));
- (SharedSkikoTypeface * _Nullable)getTypefaceIndex:(int32_t)index __attribute__((swift_name("getTypeface(index:)")));
- (SharedSkikoTypeface * _Nullable)matchStyleStyle:(SharedSkikoFontStyle *)style __attribute__((swift_name("matchStyle(style:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontFeature.Companion")))
@interface SharedSkikoFontFeatureCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontFeatureCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinArray<SharedSkikoFontFeature *> *)parseStr:(NSString *)str __attribute__((swift_name("parse(str:)")));
- (SharedSkikoFontFeature *)parseOneS:(NSString *)s __attribute__((swift_name("parseOne(s:)")));
- (SharedKotlinArray<SharedSkikoFontFeature *> *)parseW3Str:(NSString *)str __attribute__((swift_name("parseW3(str:)")));
@property (readonly) SharedKotlinArray<SharedSkikoFontFeature *> *EMPTY __attribute__((swift_name("EMPTY")));
@property (readonly) uint32_t GLOBAL_END __attribute__((swift_name("GLOBAL_END")));
@property (readonly) uint32_t GLOBAL_START __attribute__((swift_name("GLOBAL_START")));
@property (readonly) SharedSkikoPattern *_featurePattern __attribute__((swift_name("_featurePattern")));
@property (readonly) SharedSkikoPattern *_splitPattern __attribute__((swift_name("_splitPattern")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoFontStyleSet.Companion")))
@interface SharedSkikoFontStyleSetCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedSkikoFontStyleSetCompanion *shared __attribute__((swift_name("shared")));
- (SharedSkikoFontStyleSet *)makeEmpty __attribute__((swift_name("makeEmpty()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoPattern")))
@interface SharedSkikoPattern : SharedBase
- (instancetype)initWithRegex:(NSString *)regex __attribute__((swift_name("init(regex:)"))) __attribute__((objc_designated_initializer));
- (SharedSkikoMatcher *)matcherInput:(id)input __attribute__((swift_name("matcher(input:)")));
- (SharedKotlinArray<NSString *> *)splitInput:(id)input __attribute__((swift_name("split(input:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SkikoMatcher")))
@interface SharedSkikoMatcher : SharedBase
- (instancetype)initWithRegex:(SharedKotlinRegex *)regex input:(id)input __attribute__((swift_name("init(regex:input:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)groupIx:(int32_t)ix __attribute__((swift_name("group(ix:)")));
- (BOOL)matches __attribute__((swift_name("matches()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinRegex")))
@interface SharedKotlinRegex : SharedBase
- (instancetype)initWithPattern:(NSString *)pattern __attribute__((swift_name("init(pattern:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPattern:(NSString *)pattern options:(NSSet<SharedKotlinRegexOption *> *)options __attribute__((swift_name("init(pattern:options:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPattern:(NSString *)pattern option:(SharedKotlinRegexOption *)option __attribute__((swift_name("init(pattern:option:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinRegexCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsMatchInInput:(id)input __attribute__((swift_name("containsMatchIn(input:)")));
- (id<SharedKotlinMatchResult> _Nullable)findInput:(id)input startIndex:(int32_t)startIndex __attribute__((swift_name("find(input:startIndex:)")));
- (id<SharedKotlinSequence>)findAllInput:(id)input startIndex:(int32_t)startIndex __attribute__((swift_name("findAll(input:startIndex:)")));

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.7")
*/
- (id<SharedKotlinMatchResult> _Nullable)matchAtInput:(id)input index:(int32_t)index __attribute__((swift_name("matchAt(input:index:)")));
- (id<SharedKotlinMatchResult> _Nullable)matchEntireInput:(id)input __attribute__((swift_name("matchEntire(input:)")));
- (BOOL)matchesInput:(id)input __attribute__((swift_name("matches(input:)")));

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.7")
*/
- (BOOL)matchesAtInput:(id)input index:(int32_t)index __attribute__((swift_name("matchesAt(input:index:)")));
- (NSString *)replaceInput:(id)input transform:(id (^)(id<SharedKotlinMatchResult>))transform __attribute__((swift_name("replace(input:transform:)")));
- (NSString *)replaceInput:(id)input replacement:(NSString *)replacement __attribute__((swift_name("replace(input:replacement:)")));
- (NSString *)replaceFirstInput:(id)input replacement:(NSString *)replacement __attribute__((swift_name("replaceFirst(input:replacement:)")));
- (NSArray<NSString *> *)splitInput:(id)input limit:(int32_t)limit __attribute__((swift_name("split(input:limit:)")));

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.6")
*/
- (id<SharedKotlinSequence>)splitToSequenceInput:(id)input limit:(int32_t)limit __attribute__((swift_name("splitToSequence(input:limit:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSSet<SharedKotlinRegexOption *> *options __attribute__((swift_name("options")));
@property (readonly) NSString *pattern __attribute__((swift_name("pattern")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinRegexOption")))
@interface SharedKotlinRegexOption : SharedKotlinEnum<SharedKotlinRegexOption *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SharedKotlinRegexOption *ignoreCase __attribute__((swift_name("ignoreCase")));
@property (class, readonly) SharedKotlinRegexOption *multiline __attribute__((swift_name("multiline")));
@property (class, readonly) SharedKotlinRegexOption *literal __attribute__((swift_name("literal")));
@property (class, readonly) SharedKotlinRegexOption *unixLines __attribute__((swift_name("unixLines")));
@property (class, readonly) SharedKotlinRegexOption *comments __attribute__((swift_name("comments")));
@property (class, readonly) SharedKotlinRegexOption *dotMatchesAll __attribute__((swift_name("dotMatchesAll")));
@property (class, readonly) SharedKotlinRegexOption *canonEq __attribute__((swift_name("canonEq")));
+ (SharedKotlinArray<SharedKotlinRegexOption *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SharedKotlinRegexOption *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinRegex.Companion")))
@interface SharedKotlinRegexCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinRegexCompanion *shared __attribute__((swift_name("shared")));
- (NSString *)escapeLiteral:(NSString *)literal __attribute__((swift_name("escape(literal:)")));
- (NSString *)escapeReplacementLiteral:(NSString *)literal __attribute__((swift_name("escapeReplacement(literal:)")));
- (SharedKotlinRegex *)fromLiteralLiteral:(NSString *)literal __attribute__((swift_name("fromLiteral(literal:)")));
@end

__attribute__((swift_name("KotlinMatchResult")))
@protocol SharedKotlinMatchResult
@required
- (id<SharedKotlinMatchResult> _Nullable)next __attribute__((swift_name("next()")));
@property (readonly) SharedKotlinMatchResultDestructured *destructured __attribute__((swift_name("destructured")));
@property (readonly) NSArray<NSString *> *groupValues __attribute__((swift_name("groupValues")));
@property (readonly) id<SharedKotlinMatchGroupCollection> groups __attribute__((swift_name("groups")));
@property (readonly) SharedKotlinIntRange *range __attribute__((swift_name("range")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinMatchResultDestructured")))
@interface SharedKotlinMatchResultDestructured : SharedBase
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component10 __attribute__((swift_name("component10()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (NSString *)component6 __attribute__((swift_name("component6()")));
- (NSString *)component7 __attribute__((swift_name("component7()")));
- (NSString *)component8 __attribute__((swift_name("component8()")));
- (NSString *)component9 __attribute__((swift_name("component9()")));
- (NSArray<NSString *> *)toList __attribute__((swift_name("toList()")));
@property (readonly) id<SharedKotlinMatchResult> match __attribute__((swift_name("match")));
@end

__attribute__((swift_name("KotlinMatchGroupCollection")))
@protocol SharedKotlinMatchGroupCollection <SharedKotlinCollection>
@required
- (SharedKotlinMatchGroup * _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
@end

__attribute__((swift_name("KotlinIntProgression")))
@interface SharedKotlinIntProgression : SharedBase <SharedKotlinIterable>
@property (class, readonly, getter=companion) SharedKotlinIntProgressionCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (SharedKotlinIntIterator *)iterator __attribute__((swift_name("iterator()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t first __attribute__((swift_name("first")));
@property (readonly) int32_t last __attribute__((swift_name("last")));
@property (readonly) int32_t step __attribute__((swift_name("step")));
@end

__attribute__((swift_name("KotlinClosedRange")))
@protocol SharedKotlinClosedRange
@required
- (BOOL)containsValue:(id)value __attribute__((swift_name("contains(value:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
@property (readonly) id endInclusive __attribute__((swift_name("endInclusive")));
@property (readonly, getter=start_) id start __attribute__((swift_name("start")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.9")
*/
__attribute__((swift_name("KotlinOpenEndRange")))
@protocol SharedKotlinOpenEndRange
@required
- (BOOL)containsValue_:(id)value __attribute__((swift_name("contains(value_:)")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
@property (readonly) id endExclusive __attribute__((swift_name("endExclusive")));
@property (readonly, getter=start_) id start __attribute__((swift_name("start")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinIntRange")))
@interface SharedKotlinIntRange : SharedKotlinIntProgression <SharedKotlinClosedRange, SharedKotlinOpenEndRange>
- (instancetype)initWithStart:(int32_t)start endInclusive:(int32_t)endInclusive __attribute__((swift_name("init(start:endInclusive:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SharedKotlinIntRangeCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)containsValue:(SharedInt *)value __attribute__((swift_name("contains(value:)")));
- (BOOL)containsValue_:(SharedInt *)value __attribute__((swift_name("contains(value_:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isEmpty_ __attribute__((swift_name("isEmpty()")));
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.9")
*/
@property (readonly) SharedInt *endExclusive __attribute__((swift_name("endExclusive"))) __attribute__((deprecated("Can throw an exception when it's impossible to represent the value with Int type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")));
@property (readonly) SharedInt *endInclusive __attribute__((swift_name("endInclusive")));
@property (readonly, getter=start_) SharedInt *start __attribute__((swift_name("start")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinMatchGroup")))
@interface SharedKotlinMatchGroup : SharedBase
- (instancetype)initWithValue:(NSString *)value range:(SharedKotlinIntRange *)range __attribute__((swift_name("init(value:range:)"))) __attribute__((objc_designated_initializer));
- (SharedKotlinMatchGroup *)doCopyValue:(NSString *)value range:(SharedKotlinIntRange *)range __attribute__((swift_name("doCopy(value:range:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SharedKotlinIntRange *range __attribute__((swift_name("range")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinIntProgression.Companion")))
@interface SharedKotlinIntProgressionCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinIntProgressionCompanion *shared __attribute__((swift_name("shared")));
- (SharedKotlinIntProgression *)fromClosedRangeRangeStart:(int32_t)rangeStart rangeEnd:(int32_t)rangeEnd step:(int32_t)step __attribute__((swift_name("fromClosedRange(rangeStart:rangeEnd:step:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinIntRange.Companion")))
@interface SharedKotlinIntRangeCompanion : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedKotlinIntRangeCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) SharedKotlinIntRange *EMPTY __attribute__((swift_name("EMPTY")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
