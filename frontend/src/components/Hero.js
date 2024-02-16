import "../styles/HeroStyles.css";

// Functional component named Hero with props as the parameter
function Hero(props) {
  return (
    <>
      <div className={props.cName}>
        <img src={props.heroImg} alt="heroImg" />

        <div className="hero-text">
          <h1>{props.title}</h1>
          <p>{props.text}</p>

          <a href={props.url} className={props.btnClass}>
            {props.btnText}
          </a>
        </div>
      </div>
    </>
  );
}

export default Hero;
