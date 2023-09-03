import React from "react";
import surf from "./IMG_0074.JPG"
const Home = () => {
    return (
        <div style={style}>
            <div>
                <img src={surf} width="500px"/>
                <br/>
                <br/>
                <br/>
                <h1 align="center">안녕하세요...</h1>
            </div>
        </div>

    )
}
const style = {
    display : 'flex',
    justifyContent: 'center'
}
export default Home