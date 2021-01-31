
const apihost = "http://localhost:8080/"

export const search = (text) => {
    return (apihost + "api/search/" + text)
}

export const getDetails = (text) => {
    return (apihost + "api/details/" + text)
}

export const getYearBreakdown = (lotcode) => {
    return apihost + "api/breakdown/year/" + lotcode;
}

export const getVarietyBreakdown = (lotcode) => {
    return apihost + "api/breakdown/variety/" + lotcode;
}

export const getRegionBreakdown = (lotcode) => {
    return apihost + "api/breakdown/region/" + lotcode;
}

export const getYearVarietyBreakdown = (lotcode) => {
    return apihost + "api/breakdown/year-variety/" + lotcode;
}