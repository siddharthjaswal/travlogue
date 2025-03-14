


data class AutocompleteResponse(
val suggestions: List<AutocompletePrediction>
)

data class AutocompletePrediction(
val placeId: String,
val distanceMeters: Int,
val placeTypes: List<String>,
val types: List<String>,
val fullText: String,
val primaryText: String,
val secondaryText: String,
val fullTextMatchedSubstrings: List<SubstringMatch>,
val primaryTextMatchedSubstrings: List<SubstringMatch>,
val secondaryTextMatchedSubstrings: List<SubstringMatch>
)

data class SubstringMatch(
val offset: Int,
val length: Int
)



{"suggestions":[{"placeId":"ChIJgy4aag1TUkYRxMCQSXGj7Z8","distanceMeters":0,"placeTypes":["ESTABLISHMENT","POINT_OF_INTEREST"],"types":["establishment","point_of_interest"],"fullText":"Hovedbanegården, Bernstorffsgade, København, Denmark","primaryText":"Hovedbanegården","secondaryText":"Bernstorffsgade, København, Denmark","fullTextMatchedSubstrings":[{"offset":0,"length":1},{"offset":34,"length":9}],"primaryTextMatchedSubstrings":[{"offset":0,"length":1}],"secondaryTextMatchedSubstrings":[{"offset":17,"length":9}]},{"placeId":"EixIdXN1bSBUb3J2LCBDb3BlbmhhZ2VuIE11bmljaXBhbGl0eSwgRGVubWFyayIuKiwKFAoSCcmobveYUVJGERAtLaCVYekBEhQKEgmJ-XDlIlNSRhFCTaD5KgiltA","distanceMeters":0,"placeTypes":["GEOCODE","ROUTE"],"types":["geocode","route"],"fullText":"Husum Torv, Copenhagen Municipality, Denmark","primaryText":"Husum Torv","secondaryText":"Copenhagen Municipality, Denmark","fullTextMatchedSubstrings":[{"offset":0,"length":1}],"primaryTextMatchedSubstrings":[{"offset":0,"length":1}],"secondaryTextMatchedSubstrings":[]},{"placeId":"EidILiBDLiDDmHJzdGVkcyBWZWosIEvDuGJlbmhhdm4sIERlbm1hcmsiLiosChQKEgm5p67rplNSRhG5iouX_ceFARIUChIJIz2AXDxTUkYRuGeU5t1-3QQ","distanceMeters":0,"placeTypes":["GEOCODE","ROUTE"],"types":["geocode","route"],"fullText":"H. C. Ørsteds Vej, København, Denmark","primaryText":"H. C. Ørsteds Vej","secondaryText":"København, Denmark","fullTextMatchedSubstrings":[{"offset":0,"length":1},{"offset":19,"length":9}],"primaryTextMatchedSubstrings":[{"offset":0,"length":1}],"secondaryTextMatchedSubstrings":[{"offset":0,"length":9}]},{"placeId":"ChIJCYQZeAZTUkYRmXRjJDsELWE","distanceMeters":0,"placeTypes":["ESTABLISHMENT","LODGING","POINT_OF_INTEREST","OTHER"],"types":["hotel","establishment","lodging","point_of_interest"],"fullText":"Hotel Kong Arthur, Nørre Søgade, København, Denmark","primaryText":"Hotel Kong Arthur","secondaryText":"Nørre Søgade, København, Denmark","fullTextMatchedSubstrings":[{"offset":0,"length":1},{"offset":33,"length":9}],"primaryTextMatchedSubstrings":[{"offset":0,"length":1}],"secondaryTextMatchedSubstrings":[{"offset":14,"length":9}]},{"placeId":"Ei1IYXZuZWhvbG1lbiwgQ29wZW5oYWdlbiBNdW5pY2lwYWxpdHksIERlbm1hcmsiLiosChQKEgn91Or8b1NSRhGgEN5FaiX0oBIUChIJiflw5SJTUkYRQk2g-SoIpbQ","distanceMeters":0,"placeTypes":["GEOCODE","ROUTE"],"types":["geocode","route"],"fullText":"Havneholmen, Copenhagen Municipality, Denmark","primaryText":"Havneholmen","secondaryText":"Copenhagen Municipality, Denmark","fullTextMatchedSubstrings":[{"offset":0,"length":1}],"primaryTextMatchedSubstrings":[{"offset":0,"length":1}],"secondaryTextMatchedSubstrings":[]}]}