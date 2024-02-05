import Hero from "../components/Hero";

// Background image of home page
import heroImg from "../assets/heroImg.jpg";

function Home() {
  return (
    <>
      {/* Rendering the Hero component with specified props (Parent -- Home) (Child -- Hero) */}
      <Hero
        cName="hero"
        heroImg={heroImg} // Image for the hero section
        title="Your Journey Your Story" // Title for the hero section
        text="Choose Your Favourite Destination." // Text content for the hero section
        url="/bookflight"
        btnClass="show" // CSS class for styling the button
        btnText="Book Tickets" // Text content for the button
      />
    </>
  );
}

export default Home;
