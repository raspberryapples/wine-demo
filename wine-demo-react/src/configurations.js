
const apihost = "http://localhost:8080/"

export const search = (text) => {
    return (apihost + "api/search/" + text)
}
