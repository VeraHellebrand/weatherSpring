import { useEffect, useState } from "react";
import axios from "axios";
import './App.css';

function App() {
  const [weatherData, setWeatherData] = useState([]);
  const [showRoom, setShowRoom] = useState({});
  const [showData, setShowData] = useState(false);
  const [time, setTime] = useState("");
  const [selectedCity, setSelectedCity] = useState("");

  useEffect(() => {
    getData();
    const interval = setInterval(() => {
      getData();
    }, 120000);
    return () => clearInterval(interval);
  }, []);

  useEffect(() => {
    if (selectedCity !== "" && weatherData.length > 0) {
      const cityData = weatherData.find(
        (item) => item.location === selectedCity
      );
      if (cityData) {
        setShowRoom(cityData);
        setShowData(true);
      }
    }
  }, [weatherData, selectedCity]);

  const getCurrentTime = () => {
    const now = new Date();
    return now.toLocaleTimeString();
  };

  const getData = () => {
    axios
      .get("http://localhost:8090/weather")
      .then(function (response) {
        setWeatherData(response.data);
        setTime(getCurrentTime);
      })
      .catch(function (error) {
        const alertMessage = `API nedostupné: ${error}`;
        alert(alertMessage);
        console.error("There was an error!", error);
      });
  };

  const handleChange = (e) => {
    let city = e.target.value;
    if (city !== "label") {
      setShowRoom(weatherData.find((item) => item.location === city));
      setShowData(true);
      setSelectedCity(city);
    } else {
      setShowData(false);
      setSelectedCity("");
    }
  };

  return (
    <div className="container">
      <div className="row my-5 text-center">
        <h1 className="display-3">WeatherApp</h1>
      </div>
      <div className="row my-5">
        <div className="col-md-6 mx-auto">
          <select className="form-select citySelect" onChange={handleChange}>
            <option value="label">vyberte město</option>
            {weatherData.map((item, index) => (
              <option key={index} value={item.location}>
                {item.location}
              </option>
            ))}
          </select>
        </div>
      </div>
      <div className="row my-2">
        <p className="col-md-6 mx-auto">Aktualizováno: {time}</p>
      </div>
      <div className="row my-5">
        <div
          className={showData ? "col-md-6 mx-auto" : "col-md-6 mx-auto d-none"}
        >
          <h2 className="display-6 fadeIn">{showRoom.location}</h2>
          {showData && (
            <>
              <p key={selectedCity + "-1"} className="fadeIn" style={{ animationDelay: "0.5s" }}>
                Čas: {showRoom.timestamp}
              </p>
              <p key={selectedCity + "-2"} className="fadeIn" style={{ animationDelay: "1s" }}>
                Teplota: {showRoom.temp_celsius}°C
              </p>
              <p key={selectedCity + "-3"} className="fadeIn" style={{ animationDelay: "1.5s" }}>
                Rel. vlhkost: {showRoom.relative_humidity} %
              </p>
              <p key={selectedCity + "-4"} className="fadeIn" style={{ animationDelay: "2s" }}>
                Rychlost větru: {showRoom.wind_speed_m_per_s} mi/h
              </p>
              <p key={selectedCity + "-5"} className="fadeIn" style={{ animationDelay: "2.5s" }}>
                Směr větru: {showRoom.wind_direction}
              </p>
              <p key={selectedCity + "-6"} className="fadeIn">
                Počasí: {showRoom.weather_description}
              </p>
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
