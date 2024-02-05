import Hero from "../components/Hero";
import aboutUsImg from "../assets/aboutUsImg.jpg";
import AboutUs from "../components/AboutUs";

function About() {
  return (
    <>
      <Hero
        cName="hero-mid"
        heroImg={aboutUsImg}
        title="About Us"
        btnClass="hide"
      />
      <AboutUs />
    </>
  );
}

export default About;
