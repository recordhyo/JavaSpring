
function Token(){
    const token = new URL(window.location.href).searchParams.get("accessToken")
    const refreshToken = new URL(window.location.href).searchParams.get("refreshToken")
    if (token) {
        localStorage.setItem("token", token)
        localStorage.setItem("refreshToken", refreshToken)

    }
    return (
        <div></div>
    )
}
export default Token();