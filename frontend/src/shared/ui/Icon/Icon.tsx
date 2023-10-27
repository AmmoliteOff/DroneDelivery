type IconType = "weight";

interface IconProps {
    className?: string;
    onClick?: () => void;
    type: IconType;
}

export const Icon: React.FC<IconProps> = ({ className, onClick, type }) => {
    return (
        <img
            onClick={onClick}
            className={className}
            src={`${type}.png`}
            alt="icon"
        />
    );
};
